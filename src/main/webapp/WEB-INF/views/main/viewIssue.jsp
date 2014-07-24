<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="${pageContext.request.contextPath}/resources/assets/javascript/updateIssue-validation.js"></script>

<div class="container viewIssueContainer">
	<div class="viewIssue">
		<div class="viewIssueTitle">
			<h1><c:out value = "${viewIssue.title}" /></h1>
		</div>
		

		<div class="viewIssueContent">
			<p><c:out value="${viewIssue.content}"/></p>
		</div>

		<div class="viewIssueFooter">
			<div class="viewIssueFooterDetails">
				<div class="viewIssueOwner"><c:out value="${viewIssue.owner}"/></div>
				<div class="viewIssueDate">
					<small><c:out value="${viewIssue.updateDate}"/></small>
				</div>
			</div>
			<div class="viewIssueFooterButtons">
				<div id="current-state" class="<c:out value="${fn:toLowerCase(viewIssue.state)}"/> viewIssueState">
					
				</div>
				<button id="edit" type="button" class="btn btn-primary">Update
					Issue</button>
			</div>
		</div>
	</div>

	<div class="editIssueContent">
		<div>
			<label for="issueTitle">Title</label> <input id="issueTitle"
				type="text" class="form-control viewIssueTitleEdit" 
				onkeyup="ValidateTitle()"
				placeholder="Title">
				<span class="error"></span>
		</div>

		<div>
			<label for="issueContent">Content</label>
			<textarea 
			id="issueContent" 
			class="form-control viewIssueContentEdit" 
			rows="10"
			onkeyup="ValidateContent()" 
			></textarea>
			<span class="error"></span>
		</div>

		<div class="editIssueFooterButtons">
			<button id="reset" type="button" class="btn btn-default">Cancel</button>
			<button id="send" type="button" class="btn btn-primary" disabled >Save</button>
		</div>

		<div id="issue-states">
			<span class="issue-label">Change state:</span>
			<div class="opened viewIssueState" id="Opened"></div>
			<div class="testing viewIssueState" id="Testing"></div>
			<div class="closed viewIssueState" id="Closed"></div>
		</div>
	</div>
</div>
