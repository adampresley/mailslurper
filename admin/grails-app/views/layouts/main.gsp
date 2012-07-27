<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title><g:layoutTitle default="MailSlurper Admin"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="MailSlurper Administrator" />
	<meta name="author" content="Adam Presley" />

	<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css" />
	<style type="text/css">
		body { padding-top: 60px; padding-bottom: 40px; }
		.sidebar-nav { padding: 9px 0; }
	</style>
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-responsive.css')}" type="text/css" />
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css" />

	<!--[if lt IE 9]>
	<script src="${resource(dir: 'js', file: 'html5.js')}"></script>
	<![endif]-->

	<g:layoutHead/>
	<r:layoutResources />
</head>

<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="/">MailSlurper</a>
				<div class="nav-collapse">
					<ul class="nav">
						<li class="active"><a href="/"><i class="icon-home"></i> Home</a></li>
						<li class="dropdown" id="toolsMenu">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-cog"></i> Tools <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/tools/clean"><i class="icon-list-alt"></i>Clean Database</a></li>
							</ul>
						</li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span9">
				<g:layoutBody/>
			</div><!--/span-->
		</div><!--/row-->

		<hr />

		<footer>
			<p><em>Slurpin' mail since 2010!</em></p>
			<p>&copy; <a href="http://www.adampresley.com" target="_blank">Adam Presley</a> <%= new Date().format("yyyy") %></p>
		</footer>

	</div><!--/.fluid-container-->

	<script src="${resource(dir: 'js', file: 'jquery-1.7.2.js')}"></script>
	<script src="${resource(dir: 'js', file: 'bootstrap.js')}"></script>
	<script src="${resource(dir: 'js', file: 'BootstrapPlus.js')}"></script>
	<script src="${resource(dir: 'js', file: 'YAOF.js')}"></script>
	<script src="${resource(dir: 'js', file: 'jquery.blockUI.js')}"></script>
	<script src="${resource(dir: 'js', file: 'jquery.sprintf.js')}"></script>

	<g:javascript library="application"/>
	<r:layoutResources />
</body>
</html>
