<html>
<head>
	<title>Clear Database - MailSlurper Tools</title>
	<meta name="layout" content="main" />
</head>
<body>
	<h1>Clean Database</h1>
	<br />

	<div class="alert hide" id="messageWindow">
		<h3>Message:</h3>
		<p id="message">Stuff</p>
	</div>

	<section class="well">
		<p>
			Press the <strong>Clean now!</strong> button below to remove all 
			mail item records from your database. Clicking this button will,
			of course, delete all your recorded mail items.
		</p>

		<button id="btnClean" class="btn btn-primary btn-large">Clean now!</button>
	</section>

	<r:script>
		
		(function($) {
			$(document).ready(function() {
				var page = new MailSlurper.Tools.CleanPage();
			});
		})(jQuery);
		
	</r:script>

</body>
</html>