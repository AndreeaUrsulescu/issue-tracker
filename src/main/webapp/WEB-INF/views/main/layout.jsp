<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/stylesheets/style.css" type="text/css">
		
		<script src="${pageContext.request.contextPath}/resources/assets/javascript/comments.js"></script>
		
<%-- 		<script src="${pageContext.request.contextPath}/resources/assets/javascript/updateIssue.js"></script> --%>
		
		<script src="${pageContext.request.contextPath}/resources/assets/javascript/scripts.js"></script>
		
		<script src="${pageContext.request.contextPath}/resources/assets/javascript/register-validation.js"></script>
		
		<script src="${pageContext.request.contextPath}/resources/assets/javascript/issue-control.js"></script>
	
	    <script src="${pageContext.request.contextPath}/resources/assets/javascript/pagination.js"></script>
	    
	    <script src="${pageContext.request.contextPath}/resources/assets/javascript/viewIssue.js"></script>
	    
	    <script src="${pageContext.request.contextPath}/resources/assets/javascript/issue-control.js"></script>
	    
	    <script src="${pageContext.request.contextPath}/resources/assets/javascript/issueSort.js"></script>
	</head>
	<body>
			<tiles:insertAttribute name="header" />
	
			<tiles:insertAttribute name="body" />
			
			<tiles:insertAttribute name="footer"/> 
			
	</body>
</html>
