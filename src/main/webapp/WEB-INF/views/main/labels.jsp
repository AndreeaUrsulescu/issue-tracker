<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<h1 class="commentSectionTitle">Labels</h1>
	<div id="label-editor">
		<div class="labelSection">
			<span class="error"></span>
			<div class="input-group">
				<input id="tags" type="text" class="form-control">
				<div class="input-group-btn">
					<button type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown" id="label-add-btn">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>
			</div>
		</div>
	</div>

	<c:forEach var="label" items="${viewIssue.getLabels()}">
		<div id="active-labels" class="labelSection selected-labels">
			<span class="issueLabel label label-primary"> <c:out
					value="${label.getLabelName()}" /> <span
				class="glyphicon glyphicon-remove"></span>
			</span>
		</div>
	</c:forEach>

	<br>
	<div class="viewIssueContainer"></div>
</div>
