<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script
	src="${pageContext.request.contextPath}/resources/assets/javascript/pagination.js"></script>
<script type="text/javascript">
$("#searchBtn").click();
</script>

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
		<div id="filtering">
			<button id="slideFilter" type="button" onclick="slideFilter()"
				value="Filter">
				Filter <span class="caret"></span>
			</button>

			<div id="filterBox" class="row" style="display: none;">
				<div id="searchBox" class="col-xs-6">
					<form>
						<div class="form-group row">
							<label for="searchByTitle" class="col-xs-3 searchBy">Title:</label>
							<br class="hidden-md hidden-lg"> 
							<input id="searchByTitle" type="text" class="col-xs-6 textBox">
						</div>
						<div class="form-group row">
							<label for="searchByContent" class="col-xs-3 searchBy">Content:</label>
							<br class="hidden-md hidden-lg"> 
							<input id="searchByContent" class="col-xs-6 textBox" type="text">
						</div>
						<div class="form-group row">
							<label for="searchByState" class="col-xs-3 searchBy">State:</label>
							<br class="hidden-md hidden-lg"> 
							<select id="searchByState" class="col-xs-6 selectOption">
								<option value=""></option>
								<option value="New">New</option>
								<option value="Opened">Opened</option>
								<option value="Testing">Testing</option>
								<option value="Closed">Closed</option>
							</select>
						</div>
						<div class="form-group row">
							<label for="searchByCreator" class="col-xs-3 searchBy">Creator:</label>
							<br class="hidden-md hidden-lg"> 
							<input id="searchByCreator" class="col-xs-6 textBox" type="text">
						</div>
						<div class="form-group row">
							<label for="searchByAssignee" class="col-xs-3 searchBy">Assignee:</label>
							<br class="hidden-md hidden-lg"> 
							<input id="searchByAssignee" class="col-xs-6 textBox" type="text">
						</div>
						<div class="form-group row">
							<label for="searchByLabel" class="col-xs-3 searchBy">Label:</label>
							<br class="hidden-md hidden-lg"> 
							<input id="searchByLabel" class="col-xs-6 textBox" type="text">
						</div>
						<div class="row">
							<a class="btn searchBtn btn-default col-xs-4" id="searchBtn"
								onclick="searchIssues();"> <span
								class="glyphicon glyphicon-search"></span> Search
							</a>
						</div>
					</form>
				</div>

				<div id="sortBox" class="col-xs-6">
					<form>
						<div class="form-group row">
							<label for="orderBy" class="col-xs-4 searchBy">Order by:</label>
							<br class="hidden-md hidden-lg"> 
							<select id="orderBy" class="col-xs-7 selectOption">
								<option value="Date" selected="selected">Date</option>
								<option value="Title">Title</option>
							</select>
						</div>

						<div class="form-group row">
							<label for="orderType" class="col-xs-4 searchBy">Order
								type:</label>
								<br class="hidden-md hidden-lg"> 
								<select id="orderType" class="col-xs-7 selectOption">
									<option value="Descending" selected="selected">Descending</option>
									<option value="Ascending">Ascending</option>
								</select>
						</div>

						<div class="form-group">
							<label id="noSearchResults"></label>
						</div>
					</form>
				</div>
			</div>
		</div>



	</div>
	<div style="clear: both"></div>
	<div id="issues" class="row">

	</div>
	<br>

	<div id="pages">
		<label id="pageNumber"></label> <label id="total"
			style="color: rgb(47, 95, 150); font-size: 17px;" id="total">/
			${pages}</label>
	</div>
</div>
