package com.adampresley.mailslurper

import java.io.FileInputStream
import java.io.IOException
import java.io.File
import java.net.URL
import java.net.UnknownHostException
import org.apache.log4j.PropertyConfigurator
import org.apache.log4j.helpers.Loader
import java.util.Properties

import com.adampresley.mailslurper.server.WebServer

/**
 * Main entry into the application. This class fires up the mail server and
 * the embedded web server into threads. The mail server thead listens
 * on a port, and when a connection is received fires off another thread
 * to execute the request.
 * @author Adam Presley
 */
public class Main {
	static void main(String[] args) throws UnknownHostException, IOException {
		int adminPort = 8083
		
		try {
			println "Starting MailSlurper!\n"
			println "Created by Adam Presley (c) 2012"
			
			/*
			 * Start the embedded web server
			 */
			WebServer ws = new WebServer(adminPort)
			ws.start();
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
}
