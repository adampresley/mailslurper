class UrlMappings {

	static mappings = {
		"/rest/mail/add"(controller: "restMail")			{ action = [ POST: "addMailItem" ] }
		"/rest/mail"(controller: "restMail")				{ action = [ GET: "getMailItems"] }
		"/rest/mail/count"(controller: "restMail")		{ action = [ GET: "getMailItemCount"] }
		

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "home", action: "index")
		"500"(view:'/error')
	}
}
