package com.adampresley.admin

import java.util.regex.Pattern
import com.adampresley.mailslurper.log.MailLogItem

class MailService {
	def parseDataLine(String line) {
		def p = Pattern.compile("(?i)^([a-z\\-0-9]+)\\:\\s+(.*?)\$")
		def result = null

		if (line ==~ p) {
			def m = line =~ p
			result = [key: m[0][1], value: m[0][2]]
		}

		result
	}

	def addMailItem(String from, String to, String subject, String xMailer, String body) {
		new MailLogItem(
			dateSent: new Date(),
			fromAddress: from,
			toAddress: to,
			subject: subject,
			xMailer: xMailer,
			body: body
		).save(failOnError: true)
	}

	def getMailItems() {
		MailLogItem.list()
	}

	def getMailItems(offset, max, sort, order) {
		MailLogItem.list(offset: offset, max: max, sort: sort, order: order)
	}

	def getMailItem(id) {
		MailLogItem.get(id)
	}

	def getTotalCount() {
		MailLogItem.count()
	}

	def cleanMailItems() {
		MailLogItem.list()*.delete()
	}
}
