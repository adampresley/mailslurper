package mailslurperadmin

import grails.converters.JSON

class ToolsController {
	def mailService

    def clean() {

    }

    def ajax_clean() {
    	mailService.cleanMailItems()

    	def result = [success:true, message:"Database is now clean."]
    	render result as JSON
    }
}
