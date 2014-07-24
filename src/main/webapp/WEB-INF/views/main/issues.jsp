<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<c:set var="plus" value="+" />
		<c:set var="minus" value="-" />
		<label id="numberOfIssues" style="display: none">
			<c:out value="${listLength}" />
		</label>
		<label id="issuesPerPage" style="display: none">
			<c:out value="${itemsPerPage}" />
		</label>
						
		<a id="previousButton" onclick="issuePagination('${minus}','${listLength}','${itemsPerPage}')">
			<img id="left" src="${pageContext.request.contextPath}/resources/assets/images/left.png"/></a>
		<a id="nextButton" onclick="issuePagination('${plus}','${listLength}','${itemsPerPage}')">
			<img id="right" src="${pageContext.request.contextPath}/resources/assets/images/right.png"/></a>

<div id="all" >
		
	<div id="meniu">
		<div id="searchBar">
			<div id="inSearchBar">
				<label class="searchBy">Search by </label>
				<select class="searchSelect" id="selectS">
					<option value="title" selected="true">title</option>
					<option value="content" >content</option>
					<option value="state">state</option>
				 </select>
				<input type="text" id="searchField" class="searchSelect"/>
				<select class="searchSelect" id="selectT">
					<option value="new" selected="true">new</option>
					<option value="opened" >opened</option>
					<option value="testing">testing</option>
					<option value="closed">closed</option>
				 </select>
				 <a class="btn searchBtn" id="searchBtn" onclick=""> <span class="glyphicon glyphicon-search"></span> Search</a>
			</div>
		</div>
		 <a href="${pageContext.request.contextPath}/issues/createIssue" class="meniuBtn btn btn-primary">Add issue</a>
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
		<label id="pageNumber">1 </label><label style="	color: rgb(47,95,150); font-size: 17px;">/ ${pages}</label>
	</div>
</div>
