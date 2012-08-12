package com.adampresley.mailslurper.executor

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket
import java.util.StringTokenizer
import groovy.time.*
import org.codehaus.groovy.runtime.TimeCategory


/**
 * This class handles a single SMTP connection and it's requests. Commands are 
 * received and responded to here. Two logs are also kept. The first log
 * is simply for commands/responses. The second log keeps actual mail data which
 * is passed to our mailService that stores mail data in a database.
 * @author Adam Presley
 */
public class SmtpExecutor extends Thread {
	private final def __mailService
	private final Socket __socket
	
	private BufferedReader __inStream
	private PrintWriter __outStream


	/**
	 * Creates a stream reader and writer to the socket's input and output
	 * streams. This is used to communicate with a connecting client.
	 */
	public SmtpExecutor(Socket socket, mailService) throws IOException {
		this.__socket = socket
		this.__inStream = new BufferedReader(new InputStreamReader(this.__socket.getInputStream()))
		this.__outStream = new PrintWriter(new OutputStreamWriter(this.__socket.getOutputStream()))
		this.__mailService = mailService
	}
	

	/**
	 * Executes the thread. This method loops and reads commands from a connected client
	 * until such time that it receives the QUIT command. Once that command is received
	 * the thread is terminated.
	 */
	@Override
	public void run() {
		def timeToKick = null
		def maxWait = 2

		this.writeln("220 SMTP Server MailSlurper ready and waiting.")
		
		try {
			for (;;) {
				String input = ""
				
				if (this.__inStream.ready()) {
					input = this.__inStream.readLine()
					if (input == null) {
						println "Input is null. Breaking"
						break;
					}
				}
				
				/*
				 * Parse commands. For now we just ignore the command
				 * and say all is ok.
				 */
				StringTokenizer tokenizer = new StringTokenizer(input, " :")
				String command = ""
				
				if (tokenizer.hasMoreElements()) command = tokenizer.nextToken().toUpperCase()

				if (command.compareTo("DATA") == 0) {
					this.doCommand_DATA()

					/*
					 * Instead of kicking the connection out immediately
					 * let's give it a little time to send a QUIT command
					 */
					use (TimeCategory) {
						timeToKick = new Date() + maxWait.seconds
						println "Finished data recieve. Kickout time is ${timeToKick}"
					}
				}
				
				if (command.compareTo("RCPT") == 0) {
					this.writeln("250 OK, RCPT received")
					continue
				}
				
				if ((command.compareTo("MAIL") == 0) || (command.compareTo("SEND") == 0)) {
					this.writeln("250 OK, MAIL/SEND received")
					continue;
				}
				
				if ((command.compareTo("HELO") == 0) || (command.compareTo("EHLO") == 0)) {
					this.writeln ("250 OK, Howdy ya'll!")
					continue
				}
				
				if (command.compareTo("RSET") == 0) {
					this.writeln("250 OK, RSET received")
					continue
				}
				
				if (command.compareTo("QUIT") == 0) {
					this.writeln("221 SMTP Server MailSlurper closing transmission channel")
					break
				}

				/*
				 * Do we have a kill command?
				 */
				if (timeToKick) {
					if (new Date() > timeToKick) {
						println "Timeout for QUIT command expired. Closing connection"
						break
					}
				}
			} 
		} catch (Exception e) {
			e.printStackTrace()
		} finally {
			try {
				print "Closing socket connection"
				this.__socket.close()
			} catch (Exception e) {
				e.printStackTrace()
			}
		}

		println "Exiting SmtpExecutor\n"
	}
	

	/**
	 * Sends responses to the socket output stream the client is connected to.
	 * Also logs data to the default logger.
	 * @param s Message to send to the client and logger
	 */
	private void writeln(String s) {
		this.__outStream.println(s)
		this.__outStream.flush()
		
		println s
	}
	

	/**
	 * This method handles the DATA command. When the DATA command is received the
	 * input stream on the socket is read until we receive the terminating 
	 * character . (period). The actual mail contents are logged to a seperate log
	 * file.
	 * @throws IOException
	 */
	private void doCommand_DATA() throws IOException {
		def parsed
		def item = [:]

		def bodyCheckpoint = 0
		def bodyStarted = false
		def body = ""

		this.writeln("354 Send me data yo. End with .")
		println "\nReceiving mail data..."
		
		for (;;) {
			String input = this.__inStream.readLine()
			println "INPUT << ${input}"

			if (input.equals(".")) {
				if (!item["x-mailer"]) item["x-mailer"] = "N/A"

				try {
					this.__mailService.addMailItem(item.from, item.to, item.subject, item["x-mailer"], body)
				} catch (Exception addException) {
					println "Bad things happened while trying to save a record of the mail item: ${addException.message}"
					addException.printStackTrace()
				}

				println "250 OK"

				break
			} else {
				try {
					parsed = this.__mailService.parseDataLine(input)
					if (parsed) {
						item[parsed.key.toLowerCase()] = parsed.value
					} else if (bodyStarted) {
						body += input
					}

					if (input == "") bodyCheckpoint++
					if (bodyCheckpoint > 0) bodyStarted = true
				} catch (Exception ex) {
					println "Uh oh. Error in SmtpExecutor while getting data: ${ex.message}"
					ex.printStackTrace()
				}
			}
		}

		println "ENDING DATA!"
	}
}
