<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
				</select>
				<div>
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Sorted by</a>
				</div>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" id="criteria">Date<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a class="criteriaElement" href="#">Date</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" id="order"> Descending <span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a class="orderElement" href="#">Ascending</a></li>
								<li><a class="orderElement" href="#">Descending</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
			<a class="btn searchBtn" id="searchBtn" onclick="searchIssues();">
					<span class="glyphicon glyphicon-search">&nbsp;Search</span>
				</a>
		</div>
	</div>

	<div id="issues">
		<c:forEach var="issue" items="${issuesList}" varStatus="i">
			<a href="issues/issue/${issue.id}"> <span class="issue"
				id="iss${i.index}"> <span class="border"> <!--					<img class="pinB3" src="${pageContext.request.contextPath}/resources/assets/images/pin2.png"/>-->
						<label class="state">${issue.state}</label> <label class="date">${issue.updateDate}</label>
				</span> <span class="content"> <span class="title"> <c:out
								value="${issue.title}" />
					</span>
						<p>
							<c:choose>
								<c:when test="${fn:length(issue.content)>150}">
									<c:out value="${fn:substring(issue.content, 0, 150)} ..." />
								</c:when>
								<c:otherwise>
									<c:out value="${issue.content}" />
								</c:otherwise>
							</c:choose>
						</p>
				</span> <label class="owner">Updated by ${issue.owner}</label>
			</span>
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
		<!-- -->
		<a id="previousButton"
			onclick="issuePagination('${minus}','${listLength}','${itemsPerPage}')">
			<img class="arrow"
			src="${pageContext.request.contextPath}/resources/assets/images/arrow2.png" />
		</a> <label id="pageNumber">1 </label><label
			style="color: rgb(47, 95, 150); font-size: 17px;">/ ${pages}</label>
		<a id="nextButton"
			onclick="issuePagination('${plus}','${listLength}','${itemsPerPage}')">
			<img class="arrow"
			src="${pageContext.request.contextPath}/resources/assets/images/arrow.png" />
		</a>
	</div>
</div>
