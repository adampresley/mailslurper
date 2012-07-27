package mailslurperadmin

import grails.converters.JSON
import com.adampresley.mailslurper.log.MailLogItem

class RestMailController {
	def mailService

	def ok = { o ->
		response.status = 200
		render o as JSON
	}

	def error = { e -> 
		e.printStackTrace()
		response.status = 500
		render e.message
	}

	def bad = { msg -> 
		response.status = 400
		render msg
	}

	def addMailItem() {
		try {
			if (!params.from) throw new Exception("Missing argument 'from'")
			if (!params.to) throw new Exception("Missing argument 'to'")
			if (!params.subject) throw new Exception("Missing argument 'subject'")
			if (!params.xMailer) throw new Exception("Missing argument 'xMailer'")
			if (!params.body) throw new Exception("Missing argument 'body'")

			mailService.addMailItem(
				params.from,
				params.to,
				params.subject,
				params.xMailer,
				params.body
			).save(failOnError: true)

			ok([message: "New mail item inserted"])			
		} catch (Exception e) {
			bad(e.message)
		}
	}

	def getMailItems() {
		try {
			if (!params.offset) throw new Exception("Missing argument 'offset'")
			if (!params.max) throw new Exception("Missing argument 'max'")
			if (!params.sort) throw new Exception("Missing argument 'sort'")
			if (!params.order) throw new Exception("Missing argument 'order'")

			def results = mailService.getMailItems(params.offset, params.max, params.sort, params.order)
			ok([items: results])
		} catch (Exception e) {
			bad(e.message)
		}
	}

	def getMailItemCount() {
		ok([count: mailService.getTotalCount()])
	}
}
