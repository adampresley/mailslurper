import com.adampresley.mailslurper.server.SmtpServer

class BootStrap {
	def mailService

	def init = { servletContext ->
		/*
		 * Initialize the SMTP server
		 */
		String address = "127.0.0.1"
		int port = 2500
		int maxRequestsInQueue = 10

		println "Starting mail server..."
		servletContext.server = new SmtpServer(address, port, maxRequestsInQueue, mailService)
		servletContext.server.start()
	}

	def destroy = {
	}
}
