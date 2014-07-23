<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div>
	<a style="margin-bottom: 100px;" href="issues"><img
		src="${pageContext.request.contextPath}/resources/assets/images/banner.png" /></a>
	<%@ taglib prefix="sec"
		uri="http://www.springframework.org/security/tags"%>

	<sec:authorize var="loggedIn" access="isAuthenticated()" />
	<c:choose>
		<c:when test="${loggedIn}">
			<div class="dropdown control-menu">
				<button class="btn btn-default dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown">
					Hi <%=request.getUserPrincipal().getName()%>
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu"  aria-labelledby="dropdownMenu1">
					<li role="presentation">
						<a role="menuitem" tabindex="-1" href="issues"><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a>
					</li>
					<li role="presentation">
						<a role="menuitem" tabindex="-1" href="logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp;Logout</a>
					</li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="dropdown control-menu">
				<button class="btn btn-default dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown">
					Control Panel
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu"  aria-labelledby="dropdownMenu1">
					<li role="presentation">
						<a role="menuitem" tabindex="-1" href="home"><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a>
					</li>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
</div>