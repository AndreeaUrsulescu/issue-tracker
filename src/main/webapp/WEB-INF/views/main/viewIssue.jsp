<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container viewIssueContainer">
	<div class="viewIssue">
		<div class="viewIssueTitle">
			<h1>I have an issue, Lorem ipsum.</h1>
		</div>

		<div class="viewIssueContent">
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
				Nullam euismod felis vitae justo posuere euismod. Etiam sed interdum
				elit. Pellentesque interdum vel nisi ut sodales. Nullam pellentesque
				adipiscing felis, sed tristique velit mattis at. Sed et nulla
				molestie, fringilla mauris non, eleifend dui. In malesuada, orci non
				ullamcorper egestas, eros elit adipiscing purus, at eleifend massa
				lorem non erat. Duis iaculis erat a velit fringilla, ut porta quam
				blandit. Morbi ornare tempus pharetra. Vivamus vehicula tellus ut
				consectetur malesuada. Donec lobortis rutrum orci. Phasellus ut
				semper augue, at vestibulum magna. Maecenas sodales malesuada
				feugiat. Aliquam est nibh, imperdiet eget vulputate eget, dictum a
				libero. Praesent molestie mi sit amet ante blandit convallis non et
				lacus. Nunc facilisis vulputate nulla, quis congue odio interdum at.
				Aliquam erat volutpat.Proin eu rutrum tellus. Praesent nisl magna,
				tempor pellentesque elit sed, cursus vulputate ligula. Nulla
				facilisi. Etiam bibendum iaculis nulla. Nulla vitae sem varius,
				condimentum tortor in, rhoncus neque. Nulla iaculis nisi sed nibh
				dapibus facilisis. Sed mollis risus odio, viverra volutpat nisi
				accumsan non. Duis aliquam ligula eu pretium sodales. Morbi
				dignissim placerat metus, sit amet pharetra velit lacinia non. Duis
				mattis tempus convallis. Cum sociis natoque penatibus et magnis dis
				parturient.</p>
		</div>

		<div class="viewIssueFooter">
			<div class="viewIssueFooterDetails">
				<div class="viewIssueOwner">User sir Usealot</div>
				<div class="viewIssueDate">
					<small>Dummy Date 7.22.2014</small>
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