<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>
	<head>	
		<!-- dropzone css for loading bars -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/stylesheets/dropzone.css" type="text/css">
	
		<!-- Loading JQuery latest build -->
		<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

		<!-- Optional theme -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

		<!-- Latest compiled and minified JavaScript -->
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

		<!-- 		jquery UI stylesheet -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
		
		<!-- 		jquery UI JavaScript -->
		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.min.js"></script>
		
		<!-- Loading custom style -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/stylesheets/style.css" type="text/css">

		<script src="${pageContext.request.contextPath}/resources/assets/javascript/jquery.iframe-transport.js"></script>
		
		<script src="${pageContext.request.contextPath}/resources/assets/javascript/jquery.fileupload.js"></script>
		
		<script src="${pageContext.request.contextPath}/resources/assets/javascript/jquery.fileupload-process.js"></script>
		
		<script src="${pageContext.request.contextPath}/resources/assets/javascript/jquery.fileupload-validate.js"></script>

		<script src="${pageContext.request.contextPath}/resources/assets/javascript/comments.js"></script>

		<script src="${pageContext.request.contextPath}/resources/assets/javascript/register-validation.js"></script>

		<script src="${pageContext.request.contextPath}/resources/assets/javascript/issue-control.js"></script>
	    
	    <script src="${pageContext.request.contextPath}/resources/assets/javascript/bootstrap-tooltip.js"></script>
	    
	    <script src="${pageContext.request.contextPath}/resources/assets/javascript/assign-issue.js"></script>
	
	
	
	</head>
	<body>
			<tiles:insertAttribute name="header" />

			<tiles:insertAttribute name="body" />
			
			<tiles:insertAttribute name="labels" />

			<tiles:insertAttribute name="comments" />

			<tiles:insertAttribute name="footer"/> 

	</body>
</html>