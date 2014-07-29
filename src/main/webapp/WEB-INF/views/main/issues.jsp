<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="plus" value="+" />
<c:set var="minus" value="-" />
<label id="numberOfIssues" style="display: none"> <c:out
		value="${listLength}" />
</label>
<label id="issuesPerPage" style="display: none"> <c:out
		value="${itemsPerPage}" />
</label>

<a id="previousButton"
	onclick="issuePagination('${minus}','${listLength}','${itemsPerPage}')">
	<img id="left"
	src="${pageContext.request.contextPath}/resources/assets/images/left.png" />
</a>
<a id="nextButton"
	onclick="issuePagination('${plus}','${listLength}','${itemsPerPage}')">
	<img id="right"
	src="${pageContext.request.contextPath}/resources/assets/images/right.png" />
</a>

<div id="all">
	<div id="meniu">
		<a href="${pageContext.request.contextPath}/issues/createIssue"
			class="meniuBtn btn btn-primary issuesMenuButtons"><span
			class="glyphicon glyphicon-plus"></span> Add issue </a>

		<div id="searchBar">
			<div id="inSearchBar">
				<label class="searchBy">Search by </label> <select
					class="searchSelect" id="selectS">
					<option value="title" selected="selected">title</option>
					<option value="content">content</option>
					<option value="state">state</option>
				</select> <input type="text" id="searchField" class="searchSelect" /> <select
					class="searchSelect" id="selectT">
					<option value="New" selected="selected">New</option>
					<option value="Opened">Opened</option>
					<option value="Testing">Testing</option>
					<option value="Closed">Closed</option>
				</select> <label class="searchBy">Ordered by </label> <select
					class="searchSelect" id="criteria">
					<option value="Date" selected="selected">Date</option>
				</select> <select class="searchSelect" id="order">
					<option value="Descending" selected="selected">Descending</option>
					<option value="Ascending">Ascending</option>
				</select> <a class="btn searchBtn" id="searchBtn" onclick="searchIssues();">
					<span class="glyphicon glyphicon-search">&nbsp;Search</span>
				</a>
			</div>
		</div>
	</div>
	<div style="clear: both"></div>
	<div id="issues" class="row">
		<c:forEach var="issue" items="${issuesList}" varStatus="i">
			<div class="col-md-4"><a href="issues/issue/${issue.id}"> 
<!--			<span class="issue"> -->
					<c:choose>
								<c:when test="${issue.state == 'New'}">
									<span class="issue iss6"></span>
								</c:when>
								<c:when test="${issue.state == 'Opened'}">
									<span class="issue iss2"></span>
								</c:when>
								<c:when test="${issue.state == 'Testing'}">
									<span class="issue iss0"></span>
								</c:when>
								<c:otherwise>
									<span class="issue iss5"></span>				
								</c:otherwise>
					</c:choose>		
						<span class="border"> 
							<label class="state">${issue.state}</label> <label class="date">${issue.updateDate}</label>
						</span> 
						<span class="content"> <span class="title"> <c:out
								value="${issue.title}" />
						</span>
						</span>
						
							<c:choose>
								<c:when test="${fn:length(issue.content)>150}">
									<c:out value="${fn:substring(issue.content, 0, 150)} ..." />
								</c:when>
								<c:otherwise>
									<c:out value="${issue.content}" />
								</c:otherwise>
							</c:choose>
						
				<span> <label class="owner">Updated by ${issue.owner}</label>
				</span>
			</a>
			</div>
		</c:forEach>
	</div>
	<br>

	<div id="pages">
		<label id="pageNumber">1 </label><label id="total"
			style="color: rgb(47, 95, 150); font-size: 17px;" id="total">/ ${pages}</label>
	</div>
</div>
