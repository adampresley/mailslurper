<html>
<head>
	<title>Send Test Email - MailSlurper Tools</title>
	<meta name="layout" content="main" />
</head>
<body>
	<h1>Send Test Email</h1>
	<br />

	<div class="alert hide" id="messageWindow">
		<h3>Message:</h3>
		<p id="message">Stuff</p>
	</div>

	<form class="well" name="frmTest" id="frmTest">
		<p>
			Fill in the information below and press the <strong>Send Test Email</strong>
			button to send a test email to MailSlurper.
		</p>

		<label for="fromAddress">From:</label>
		<input type="email" name="fromAddress" id="fromAddress" value="from@test.com" maxlength="255" class="span4" />

		<label for="toAddress">To:</label>
		<input type="email" name="toAddress" id="toAddress" value="to@test.com" maxlength="255" class="span4" />

		<label for="subject">Subject:</label>
		<input type="text" name="subject" id="subject" value="Test Message - Subject" maxlength="512" class="span5" />

		<label for="message">Message:</label>
		<textarea name="emailMessage" id="emailMessage" rows="10" class="span5"></textarea>

		<br />
		<button id="btnSend" type="button" class="btn btn-primary btn-large">Send Test Email</button>
	</form>

	<r:script>
		
		(function($) {
			$(document).ready(function() {
				var page = new MailSlurper.Tools.TestPage();
			});
		})(jQuery);
		
	</r:script>

</body>
</html>