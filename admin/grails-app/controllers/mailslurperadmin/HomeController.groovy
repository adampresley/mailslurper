package mailslurperadmin

import grails.converters.JSON

class HomeController {
	def mailService

	def index() {
		if (!params.offset) params.offset = 0
		if (!params.max) params.max = 10
		if (!params.sort) params.sort = "dateSent"
		if (!params.order) params.order = "desc"

		def sortMap = [
			dateSent: "Date Sent",
			fromAddress: "From Address",
			toAddress: "To Address",
			subject: "Subject"
		]

		def orderMap = [ desc: "descending", asc: "ascending" ]

		[
			mailItems: mailService.getMailItems(params.offset, params.max, params.sort, params.order),
			sortDescription: sortMap[params.sort],
			orderDescription: orderMap[params.order],
			total: mailService.getTotalCount()
		]
	}

	def ajax_getMailItem() {
		render mailService.getMailItem(params.id) as JSON
	}
}
