MailSlurper = {
	Tools: {}
};

MailSlurper.block = function(msg) {
	$.blockUI({ message: "<h3 style=\"padding: 10px;\">" + msg + "</h3>", baseZ: 2010 });
};


MailSlurper.Index = function(config) {
	this.popupMailItem = function(id) {
		$.ajax({
			url: "/home/ajax_getMailItem",
			dataType: "json",
			data: { id: id },
			success: function(data) {
				new BootstrapPlus.Modal({
					header: data.subject,
					body: data.body
				});
			}
		});
	};

	var
		__config = $.extend(config, {}),
		__this = this;

	/*
	 * Constructor
	 */
	$(".viewMailItemIcon").click(function() {
		__this.popupMailItem($(this).attr("data-id"));
	});
};
YAOF.attach(MailSlurper.Index);


MailSlurper.Tools.CleanPage = function(config) {
	var
		__init = function() {
			$("#btnClean").click(__doClean);
		},

		__doClean = function() {
			MailSlurper.block("Cleaning...");
			$.ajax({
				url: "/tools/ajax_clean",
				success: function(data) {
					$("#message").html(data.message);
					$("#messageWindow").addClass("alert-success").removeClass("hide");
					$.unblockUI();

					__this.publish("mailslurper.clean", { message: "Database cleaned" });
				},
				error: function() {
					$("#message").html("Something has gone wrong trying to clean your database!");
					$("#messageWindow").addClass("alert-error").removeClass("hide");
					$.unblockUI();
				}
			});
		},

		__config = $.extend(config, {

		}),
		__this = this;

	__init();
};
YAOF.attach(MailSlurper.Tools.CleanPage);


MailSlurper.Tools.TestPage = function(config) {
	this.sendTestMessage = function(from, to, subject, message) {
		MailSlurper.block("Sending message...");
		$.ajax({
			url: "/tools/ajax_test",
			data: {
				fromAddress: from,
				toAddress: to,
				subject: subject,
				emailMessage: message
			},
			success: function(data) {
				$.unblockUI();
				$("#message").html("<p>The test message should now be on its way.</p>");
				$("#messageWindow").addClass("alert-success").removeClass("hide");
				$("#fromAddress").focus();
			}
		});
	};


	var
		__config = $.extend(config, {}),
		__this = this;


	/*
	 * Constructor
	 */
	$("#emailMessage").wysiwyg({
		rmUnusedControls: true,
		controls: {
			bold: { visible: true },
			italic: { visible: true },
			underline: { visible: true },
			paragraph: { visible: true },
			h1: { visible: true },
			h2: { visible: true },
			h3: { visible: true },
			cut: { visible: true },
			copy: { visible: true },
			paste: { visible: true },
			html: { visible: true }
		},
		initialContent: "<p>This is a <em>test</em> message from MailSlurper.</p>"
	});

	$("#btnSend").click(function() { 
		__this.sendTestMessage(
			$("#fromAddress").val(),
			$("#toAddress").val(),
			$("#subject").val(),
			$("#emailMessage").val()
		);
	});

	$("#fromAddress").focus();
};
YAOF.attach(MailSlurper.Tools.TestPage);
