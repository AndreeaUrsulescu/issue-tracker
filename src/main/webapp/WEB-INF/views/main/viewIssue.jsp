<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container viewIssueContainer">
	<div class="viewIssue">
		<div class="viewIssueTitle">
			<h1>
				<c:out value="${viewIssue.getTitle()}"/>
			</h1>
		</div>
		

		<div class="viewIssueContent">
			<p>
				<c:out value="${viewIssue.getContent()}"/>
			</p>
		</div>

		<div class="viewIssueFooter">
			<div class="viewIssueFooterDetails">
				<div class="viewIssueOwner">
					<c:out value="${viewIssue.getOwner().getUserName()}"/>
				</div>
				<div class="viewIssueDate">
					<small>
						<c:out value="${viewIssue.getUpdateDate()}"/>
					</small>
				</div>
			</div>
			<div class="viewIssueFooterButtons">
				<div class="new viewIssueState"></div>
				<button id="edit" type="button" class="btn btn-primary">Update
					Issue</button>
			</div>
		</div>
	</div>

	<div class="editIssueContent">
		<div>
			<label for="issueTitle">Title</label> <input id="issueTitle"
				type="text" class="form-control viewIssueTitleEdit"
				placeholder="Title">
		</div>

		<div>
			<label for="issueContent">Content</label>
			<textarea id="issueContent" class="form-control viewIssueContentEdit" rows="10"></textarea>
		</div>

		<div class="editIssueFooterButtons">
			<button id="reset" type="button" class="btn btn-default">Cancel</button>
			<button id="send" type="button" class="btn btn-primary" >Save</button>
		</div>

		<div id="issue-states">
			<span class="issue-label">Change state:</span>
			<div class="new viewIssueState"></div>
			<div class="opened viewIssueState"></div>
			<div class="testing viewIssueState"></div>
			<div class="closed viewIssueState"></div>
		</div>
	</div>
</div>
