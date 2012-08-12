package mailslurperadmin

import grails.converters.JSON

class ToolsController {
	def slurperService

	def clean() { }

	def ajax_clean() {
		slurperService.cleanMailItems()

		def result = [success:true, message:"Database is now clean."]
		render result as JSON
	}

	def test() { }

	def ajax_test() {
		try {
			sendMail {
				to params.toAddress
				from params.fromAddress
				subject params.subject
				body params.emailMessage
			}
		} catch (Exception ex) {}
		
		render "ok"
	} 
}
