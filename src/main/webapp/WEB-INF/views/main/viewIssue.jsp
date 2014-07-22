<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <button type="button" onclick="updateIssue()">Save</button> -->
<!-- <button type="button" onclick="editIssue()">Edit</button> -->

<script src="resources/assets/javascript/updateIssue-validation.js"></script>
<div class="container">

	<div class="viewIssue">
		
		<div class="viewIssueHeader">
		
			<div class="viewIssueHeaderTitle">
				<h1> I have an issue, I'm an alcoholic. </h1>
			</div>
			
			<div class="viewIssueHeaderState">
				
				<div class="new viewIssueState" >
					New
				</div>
				
				<div class="opened viewIssueState">
					Opened
				</div>
				
				<!-- Don't use progress as a class, bootstrap has it's own. Lesson learned. -->
				
				<div class="inProgress viewIssueState">
					In progress
				</div>
				
				<div class="testing viewIssueState">
					In testing
				</div>
				
				<div class="closed viewIssueState">
					Closed
				</div>
				
			</div>
		
		</div>
		
		<div class="viewIssueContent">
		
		</div>
		
		<div class="viewIssueFooter">
		
			<div class="viewIssueOwner">
			
			</div>
			
			<div class="viewIssueDate">
			
			</div>
		
		</div>
		
		<!-- Pentru omul rau -->
		
		<div class="omRau">
			<input type="text" class="form-control" id="Title" placeholder="Title">
			<textarea class="viewIssueContentEdit" rows="10" id="Content"></textarea>
		</div>
		
	</div>

</div>