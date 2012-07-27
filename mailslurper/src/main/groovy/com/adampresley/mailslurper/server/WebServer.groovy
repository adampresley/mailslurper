package com.adampresley.mailslurper.server

import java.io.File
import java.util.zip.ZipFile
import groovy.util.AntBuilder

import org.mortbay.jetty.*
import org.mortbay.jetty.nio.SelectChannelConnector
import org.mortbay.jetty.webapp.WebAppContext


/**
 * WebServer provides a thread for an embedded web server. This makes use
 * of Jetty, extracts the WAR file named admin.war to a temporary location,
 * then starts the web server on port 8083.
 * @author Adam Presley
 */
class WebServer extends Thread {
	private int __port
	
	WebServer(int port) {
		this.__port = port
		println "Jetty web server starting at localhost on port ${this.__port}"
	}
	

	@Override
	void run() {
		String currentDir = ""
		String webRoot = ""
		String subdir = "mailslurperadmin"
		String warFileLocation = ""
		def ant = new AntBuilder()

		Server instance = null
		Connector connector = null

		try {
			currentDir = new File(".").getCanonicalPath()
			warFileLocation = String.format("%s%sdist%sadmin.war", currentDir, File.separatorChar, File.separatorChar)
			webRoot = System.getProperty("java.io.tmpdir") + File.separatorChar + subdir

			new File(webRoot).mkdir()


			/*
			 * Extract the WAR here
			 */
			println "Preparing administrator..."
			ant.unzip(src: warFileLocation, dest: webRoot, overwrite: true)

			instance = new Server()
			connector = new SelectChannelConnector()

			connector.setPort(this.__port)
			connector.setHost("127.0.0.1")
			instance.addConnector(connector)

			WebAppContext wac = new WebAppContext()
			wac.setContextPath("/")
			wac.setWar(webRoot)
			wac.setParentLoaderPriority(true)

			instance.addHandler(wac)
			instance.setStopAtShutdown(true)

			println "Access the MailSlurper admin by browsing to http://localhost:${__port}"

			instance.start()			
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
}
