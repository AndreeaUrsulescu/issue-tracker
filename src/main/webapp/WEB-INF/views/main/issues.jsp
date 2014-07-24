<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="all" >
	<div id="meniu">
		 <a href="${pageContext.request.contextPath}/issues/createIssue" class="meniuBtn btn btn-primary">Add issue</a>
	</div>
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				        <span class="sr-only">Toggle navigation</span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				      </button>
				      <a class="navbar-brand" href="#">Sort by</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="#criteria"> Criteria <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
					            <li><a href="#">Date</a></li>
					    	</ul>
				    	</li>
				    	<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="#order"> Order <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
					            <li><a href="#">Ascending</a></li>
					            <li><a href="#">Descending</a></li>
					    	</ul>
				    	</li>
				    </ul>
		         </div>
			</div>
		</nav>
	</div>
	<div id="issues">
		<c:forEach var="issue" items="${issuesList}" varStatus="i">
			<a href="issues/issue/${issue.id}">
				<div class="issue" id="iss${i.index}">
					<div class="border">
						<!--					<img class="pinB3" src="${pageContext.request.contextPath}/resources/assets/images/pin2.png"/>-->
						<label class="state">${issue.state}</label> 
						<label class="date">${issue.updateDate}</label>
					</div>
					<div class="content">
						<h4 class="title">
							<c:out value="${issue.title}" />
						</h4>
						<p>
							<c:choose>
								<c:when test="${fn:length(issue.content)>150}">
									<c:out value="${fn:substring(issue.content, 0, 150)} ..." />
								</c:when>
								<c:otherwise>
									<c:out value="${issue.content}" />
								</c:otherwise>
							</c:choose>
							<!--		<c:if test="${fn:length(issue.title)>150}">
								<c:out value="${fn:substring(issue.title, 0, 150)} ..."/>
						</c:if> -->
						</p>
					</div>
					<label class="owner">Updated by ${issue.owner}</label>
				</div>
			</a>
		</c:forEach>
	</div>

	<br>
	<div id="pages">
		<c:set var="plus" value="+" />
		<c:set var="minus" value="-" />
		<p id="numberOfIssues" style="display: none">
			<c:out value="${listLength}" />
		</p>
		<p id="issuesPerPage" style="display: none">
			<c:out value="${itemsPerPage}" />
		</p>

		<button id="previousButton" onclick="issuePagination('${minus}','${listLength}','${itemsPerPage}')">Previous</button>
		<p id="pageNumber">1</p>
		<button id="nextButton" onclick="issuePagination('${plus}','${listLength}','${itemsPerPage}')">Next</button>
	</div>
</div>
