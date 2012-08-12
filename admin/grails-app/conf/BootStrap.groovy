import com.adampresley.mailslurper.server.SmtpServer

class BootStrap {
	def slurperService

	def init = { servletContext ->
		/*
		 * Initialize the SMTP server
		 */
		String address = "127.0.0.1"
		int port = 2500
		int maxRequestsInQueue = 10

		println "Starting mail server..."
		servletContext.server = new SmtpServer(address, port, maxRequestsInQueue, slurperService)
		servletContext.server.start()
	}

	def destroy = {
	}
}
