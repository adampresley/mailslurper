package com.adampresley.mailslurper.server

import java.net.*
import java.io.*
import java.util.concurrent.*

import com.adampresley.mailslurper.executor.SmtpExecutor


/**
 * Provides a server to listen on a port for SMTP requests. When a connection is received
 * a new thread is fired off. More specifically a SmtpExecutor object is created and
 * started.
 * @author Adam Presley
 */
 class SmtpServer extends Thread {
	private String __address
	private int __port
	private int __maxRequestsInQueue
	
	private ServerSocket __connection
	private final def __mailService

	SmtpServer(String Address, int Port, int MaxRequestsInQueue, mailService) throws UnknownHostException, IOException {
		this.__address = Address
		this.__port = Port
		this.__maxRequestsInQueue = MaxRequestsInQueue
		this.__mailService = mailService

		println "SMTP server starting at ${this.__address} on port ${this.__port.toString()}"
		this.__connection = new ServerSocket(this.__port, this.__maxRequestsInQueue, InetAddress.getByName(this.__address))
	}
	

	@Override
	void run() {
		try {
			for(;;) {
				/*
				 * Attempt to start listening for incoming connections.
				 */
				try {
					println "MailSlurper SMTP server accepting connections..."
					Socket socket = this.__connection.accept()
	
					Thread executor = new SmtpExecutor(socket, this.__mailService)
					executor.start()
				} catch (IOException e) {
					println "An exception occured while attempting to listen for connections: ${e.getMessage()}"
					e.printStackTrace()
					break
				}
			}
		} catch (Throwable e) {
			println "An exception occured during the listener loop: ${e.getMessage()}"
		}
	}
}
