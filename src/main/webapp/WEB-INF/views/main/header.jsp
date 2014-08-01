<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div>
	<a style="margin-bottom: 100px;" href="${pageContext.request.contextPath}/issues"><img
		src="${pageContext.request.contextPath}/resources/assets/images/banner.png" /></a>
	

	<sec:authorize var="loggedIn" access="isAuthenticated()" />
	<c:choose>
		<c:when test="${loggedIn}">
			<div class="dropdown control-menu">
			
				<button class="btn btn-primary dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown">
					<span class=" glyphicon glyphicon-tasks">
				
					<span style="font-family: 'Arial Black', Gadget, sans-serif;">My profile</span>
					<span class="caret"></span>
					</span>
				</button>
				<ul class="dropdown-menu" role="menu"  aria-labelledby="dropdownMenu1">
					<li role="presentation" class="dropdown-header">
						<span class="glyphicon glyphicon-user"></span>&nbsp;
							Hello <%=request.getUserPrincipal().getName()%>
					</li>
					<li role="presentation">
						<a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/issues"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;Home</a>
					</li>
					<li role="presentation">
						<a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;Logout</a>
					</li>
				</ul>
			</div>
				<div class="dropdown control-menu" id="chatRooms">
			
				<button class="btn btn-primary dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown">
					<span class=" glyphicon glyphicon-user">
					<span style="font-family: 'Arial Black', Gadget, sans-serif;">Chat rooms</span>
					<span class="caret"></span>
					</span>
				</button>
				<ul class="dropdown-menu" role="menu"  aria-labelledby="dropdownMenu1">
					<li role="presentation">
						<a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/issues"><span class="glyphicon glyphicon-th-large"></span>&nbsp;&nbsp;All issues</a>
					</li>
					<li role="presentation">
						<a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;Off-topic</a>
					</li>
				</ul>
			</div>
		</c:when>

		<c:otherwise>
			<div class="dropdown control-menu">
				
				<button class="btn btn-default dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown">
					Control Panel
					 <span class="glyphicon glyphicon-cog">
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu"  aria-labelledby="dropdownMenu1">
					<li role="presentation">
						<a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a>
					</li>
				</ul>
			</div>
		</c:otherwise> 
	</c:choose>
</div>