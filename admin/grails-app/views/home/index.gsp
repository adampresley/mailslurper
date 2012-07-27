<html>
<head>
	<title>MailSlurper Manager</title>
	<meta name="layout" content="main" />
</head>
<body>
	<h1>Mail Items</h1>
	<br />

	<g:if test="${mailItems.size() > 0}">
		<div class="alert alert-info">
			<p style="padding-top: 7px;">Sorting by <strong>${sortDescription} ${orderDescription}</strong></p>
		</div>

		<table class="table table-striped">
			<tr>
				<th>&nbsp;</th>
				<g:sortableColumn title="Subject" property="subject" />
				<g:sortableColumn title="Date" property="dateSent" />
				<g:sortableColumn title="From" property="fromAddress" />
				<g:sortableColumn title="To" property="toAddress" />
			</tr>
			<g:each in="${mailItems}" var="item" status="index">
				<tr>
					<td><a href="javascript:void(0);" class="viewMailItemIcon" data-id="${item.id}"><i class="icon-search"></i></a></td>
					<td class="subject">${item["subject"]}</td>
					<td class="date">${item.dateSent.format("YYYY-mm-dd h:mma")}</td>
					<td class="from">${item.fromAddress}</td>
					<td class="to">${item.toAddress}</td>
				</tr>
			</g:each>
		</table>
		<div class="pagination">
			<g:paginate total="${total}" max="10" />
		</div>
	</g:if>
	<g:else>
		<p>There are no mails to view. Go get social and send some mails yo!</p>
	</g:else>
	
	<r:script>
		
		(function($) {
			$(document).ready(function() {
				var indexPage = new MailSlurper.Index();
			});
		})(jQuery);
		
	</r:script>
</body>
</html>