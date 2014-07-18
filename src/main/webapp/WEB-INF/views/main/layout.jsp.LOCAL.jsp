<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
	<head>
	
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		
		<!-- Optional theme -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		
		<link rel="stylesheet" href="resources/assets/stylesheets/style.css" type="text/css">
		
		<script src='resources/assets/scripts/scripts.js'></script>
		
		<script src='resources/assets/scripts/jquery.js'></script>

	</head>
	<body>
			<tiles:insertAttribute name="header" />
	
			<tiles:insertAttribute name="body" />
	</body>
</html>