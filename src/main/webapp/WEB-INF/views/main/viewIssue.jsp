<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script
	src="${pageContext.request.contextPath}/resources/assets/javascript/updateIssue-validation.js"></script>


<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    theme: "modern",
    height : "250" ,
    plugins: [
        "advlist autolink lists link charmap print preview pagebreak",
        "searchreplace wordcount visualblocks visualchars code fullscreen",
        "insertdatetime  nonbreaking save contextmenu directionality",
        "emoticons template paste textcolor colorpicker textpattern"
    ],
    toolbar1: "insertfile undo redo | styleselect | bold italic | link print preview media | forecolor backcolor emoticons",
    image_advtab: true,
    templates: [
        {title: 'Test template 1', content: 'Test 1'},
        {title: 'Test template 2', content: 'Test 2'}
    ]
});
</script>

<div class="container viewIssueContainer">

	<div class="editIssueView row">
		<div class="col-xs-8">
			<div class="xViewIssueHeader">
				<div class="viewIssueTitle">
					<c:out value="${viewIssue.title}" />
				</div>
			</div>

			<div class="viewIssueContent">
			${viewIssue.content}
				
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
				<span class="viewIssueOwnerSeparator"> <span>Creator:</span>
					<span><c:out value="${viewIssue.owner}" /></span>
				</span> <br> <span class="viewIssueOwnerSeparator"> <span>Assignee:
				</span> <c:choose>
						<c:when test="${empty viewIssue.assignee}">
							<span id="assign">Unassigned</span>
						</c:when>
						<c:otherwise>
							<span id="assign">${viewIssue.assignee}</span>
						</c:otherwise>
					</c:choose>

				</span> <br> <span class="viewIssueOwnerSeparator"> <span>Update
						Date:</span> <small><c:out value="${viewIssue.lastDate}" /></small>
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
</div>

<div class="editIssueContent container">
	<div class="col-xs-8">
		
		<div>
			<label for="issueTitle">Title</label> <input id="issueTitle"
				type="text" class="form-control viewIssueTitleEdit"
				onkeyup="ValidateTitle()" placeholder="Title"> <span
				class="error"></span>
		</div>
	
		<div>
			<label for="issueContent">Content</label>
			<textarea name="content" id="issueContent" class="form-control viewIssueContentEdit"
				rows="10" onkeyup="ValidateContent()"></textarea>
			<span class="error"></span>
		</div>
		<div class="editIssueFooterAndStates">
			<div class="editIssueFooterButtons">
				<button id="reset" type="button" class="btn btn-default">Cancel</button>
				<button id="send" type="button" class="btn btn-primary" >Save</button>
			</div>
		
			<div id="issue-states">
				<span class="issue-label">Change state:</span>
				<div class="opened viewIssueState" id="Opened"></div>
				<div class="testing viewIssueState" id="Testing"></div>
				<div class="closed viewIssueState" id="Closed"></div>
			</div>
		</div>
		
		<div class="editIssueAssignee">
			<div class="form-group">
				<span class="error"></span>
				<br> 
				<label for="assigneeInput">Assignee:</label>
				
				<c:choose>
					<c:when test="${empty viewIssue.assignee}">
						<br id="assigneeBr" >
						<span id="assigneeLabel" style="display:none;">${viewIssue.assignee}</span>
						<span id="unassignButton" onclick="unassignUser();"
							style="display: none;"><span class="glyphicon glyphicon-remove"></span></span>
							<input type="text" class="form-control" id="assigneeInput"
							placeholder="Choose an user">
							
						<button class="btn btn-warning" onclick="assignUser();"
							id="assignButton">Assign</button>
					</c:when>
					<c:otherwise>
						<br id="assigneeBr" style="display:none">
						<span id="assigneeLabel">${viewIssue.assignee}</span>
						<span id="unassignButton" onclick="unassignUser();"
							><span class="glyphicon glyphicon-remove"></span></span>
							<input type="text" class="form-control" id="assigneeInput" style="display:none;"
							placeholder="Choose an user">
							
						<button class="btn btn-warning" onclick="assignUser();"
							id="assignButton" style="display:none;">Assign</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
	
	</div>
</div>

<script
	src="${pageContext.request.contextPath}/resources/assets/javascript/pagination.js"></script>



