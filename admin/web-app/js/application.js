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
				console.log(data);
				new BootstrapPlus.Modal({
					header: data.subject,
					body: data.body
				});
			}
		});
	};

	var
		__init = function() {
			$(".viewMailItemIcon").click(function() {
				__this.popupMailItem($(this).attr("data-id"));
			});
		},

		__config = $.extend(config, {

		}),
		__this = this;

	__init();
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
