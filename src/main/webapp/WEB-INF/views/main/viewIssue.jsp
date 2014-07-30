<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script
	src="${pageContext.request.contextPath}/resources/assets/javascript/updateIssue-validation.js"></script>

<div class="container viewIssueContainer">

	<div class="editIssueView row">
		<div class="col-xs-8">
			<div class="xViewIssueHeader">
				<div class="viewIssueTitle">
					<c:out value="${viewIssue.title}" />
				</div>
			</div>
	
			<div class="viewIssueContent">
				<c:out value="${viewIssue.content}" />
			</div>
			<button id="edit" type="button" class="btn btn-primary pull-right">Edit
		Issue</button>
			<span class="error"></span>
		</div>
		<div class="col-xs-4 viewIssueInfo">
			<div class="xStateActive">
				<span class="xStateActiveLabel">State:</span>
				<div id="current-state"
					class="<c:out value="${fn:toLowerCase(viewIssue.state)} class"/> viewIssueState"></div>
			</div>
			<br>
	
			<div class="viewIssueOwner">
				<span class="viewIssueOwnerSeparator">
				<span>Creator:</span> <span><c:out
						value="${viewIssue.owner}" /></span> 
				</span>
						<br>
				<span class="viewIssueOwnerSeparator">
					<span>Assignee: </span> 
					<span>Assignee</span> 
				</span>	
				<br> 
				<span class="viewIssueOwnerSeparator">
					<span>Update Date:</span> 
					<small><c:out value="${viewIssue.updateDate}" /></small>
				</span>
			</div>
			
			<div id="active-labels" class="labelSectionView selected-labels">
				<c:forEach var="label" items="${viewIssue.getLabels()}">
					<span class="issueLabel label label-primary"> <c:out
							value="${label.getLabelName()}" /> <span
						class="label-remove glyphicon glyphicon-remove"></span>
					</span>
				</c:forEach>
			</div>
			
						
		</div>
		<div class="clear"></div>

	</div>
<div class="form-group">
						    <span class="error"></span><br>
							<label for="assigneeInput">Assignee:</label>
							<input type="text" class="form-control" id="assigneeInput" placeholder="Choose an user">
						</div>
						<button class="btn btn-warning" onclick="assignUser();" id="assignButton">Assign</button>
						    <c:choose>
								<c:when test="${empty viewIssue.assignee}">
									<button id="unassignButton" onclick="unassignUser();" style="background-color:red;visibility:hidden">X</button>
								</c:when>
								<c:otherwise>
									<button id="unassignButton" onclick="unassignUser();" style="background-color:red;">X</button>
								</c:otherwise>
							</c:choose>
				</div>
	

	<!-- 		<div class="form-group"> -->
	<!-- 			<label for="assigneeInput">Assignee:</label> <input type="text" -->
	<!-- 				class="form-control" id="assigneeInput" placeholder="Choose an user"> -->
	<!-- 		</div> -->
	<!-- 		<button class="btn btn-warning" onclick="assignUser();">Assign</button> -->




	<div class="editIssueContent">
		<div>
			<label for="issueTitle">Title</label> <input id="issueTitle"
				type="text" class="form-control viewIssueTitleEdit"
				onkeyup="ValidateTitle()" placeholder="Title"> <span
				class="error"></span>
		</div>

		<div>
			<label for="issueContent">Content</label>
			<textarea id="issueContent" class="form-control viewIssueContentEdit"
				rows="10" onkeyup="ValidateContent()"></textarea>
			<span class="error"></span>
		</div>

		<div class="editIssueFooterButtons">
			<button id="reset" type="button" class="btn btn-default">Cancel</button>
			<button id="send" type="button" class="btn btn-primary" disabled>Save</button>
		</div>

		<div id="issue-states">
			<span class="issue-label">Change state:</span>
			<div class="opened viewIssueState" id="Opened"></div>
			<div class="testing viewIssueState" id="Testing"></div>
			<div class="closed viewIssueState" id="Closed"></div>
		</div>
	</div>
</div>


