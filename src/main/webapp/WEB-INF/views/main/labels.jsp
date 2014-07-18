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
	<div id="active-labels" class="labelSection selected-labels">
		<!--TEMPLATE HERE -->
<!-- 		<span class="issueLabel label label-primary"> java <span -->
<!-- 			class="glyphicon glyphicon-remove"></span> -->
<!-- 		</span> -->
	</div>
	<br>
	<div class="viewIssueContainer"></div>
</div>
