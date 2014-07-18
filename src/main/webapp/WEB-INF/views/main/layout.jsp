<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

	<head>
	
		<!-- Loading JQuery latest build -->
		<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		
		<!-- Optional theme -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		
		<!-- Loading custom style -->
		<link rel="stylesheet" href="resources/assets/stylesheets/style.css" type="text/css">

	</head>

			<tiles:insertAttribute name="header" />
	
			<tiles:insertAttribute name="body" />
			
			<tiles:insertAttribute name="footer"/>
