<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
	<a style="margin-bottom: 100px;" href="issues"><img
		src="${pageContext.request.contextPath}/resources/assets/images/banner.png" /></a>
<%-- 	<sec:authorize access="isAuthenticated()"> --%>
<!-- 	    authenticated as -->
<%--     <sec:authentication property="principal.username" /> --%>
<%-- 	</sec:authorize> --%>
</div>