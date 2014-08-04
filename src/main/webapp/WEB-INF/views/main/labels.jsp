<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="editIssueContent container">


		<div id="label-editor">
			<div class="row container">
				<div class="col-xs-8 labelEditorCol">
					<label>Labels:</label>
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
			</div>
	
	<div id="active-labels" class="row labelSection ">
	<br/>
		<div class="col-xs-8 selected-labels">
			<c:forEach var="label" items="${viewIssue.getLabels()}">
				<span class="issueLabel label label-primary"> <c:out
						value="${label.getLabelName()}" /> <span
					class="label-remove glyphicon glyphicon-remove"></span>
				</span>
			</c:forEach>
		</div>
	</div>
	<br>
	</div>
</div>
