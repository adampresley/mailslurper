package com.adampresley.mailslurper.log

class MailLogItem {
	Date dateSent
	String fromAddress
	String toAddress
	String subject
	String xMailer
	String body

	static constraints = {
	}

	static mapping = {
		body type: "text"
	}
}
