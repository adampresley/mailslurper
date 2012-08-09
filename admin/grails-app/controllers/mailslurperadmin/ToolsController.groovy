package mailslurperadmin

import grails.converters.JSON

class ToolsController {
	def mailService

    def clean() { }

    def ajax_clean() {
    	mailService.cleanMailItems()

    	def result = [success:true, message:"Database is now clean."]
    	render result as JSON
    }

    def test() { }

    def ajax_test() {
    	println "ajax_test: ${params}"
    	mailService.sendMail {
    		to params.toAddress
    		from params.fromAddress
    		subject params.subject
    		body params.emailMessage
    	}

    	render "ok"
    }
}
