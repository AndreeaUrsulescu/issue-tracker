<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- <button type="button" onclick="updateIssue()">Save</button> -->
<!-- <button type="button" onclick="editIssue()">Edit</button> -->

<script src="resources/assets/javascript/updateIssue-validation.js"></script>

<div class="container">

	<div class="viewIssue">
		
		<div class="viewIssueHeader">
		
			<div class="viewIssueHeaderTitle">
				<h1> I have an issue, I'm an alcoholic. </h1>
				<%-- <c:forEach items='${viewIssue.comments}' var='i'> 
					<c:out value='${i.content}' />
				</c:forEach> --%>
			</div>
		
		</div>
		
		<div class="viewIssueContent">
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam euismod felis vitae justo posuere euismod. Etiam sed interdum elit. Pellentesque interdum vel nisi ut sodales. Nullam pellentesque adipiscing felis, sed tristique velit mattis at. Sed et nulla molestie, fringilla mauris non, eleifend dui. In malesuada, orci non ullamcorper egestas, eros elit adipiscing purus, at eleifend massa lorem non erat. Duis iaculis erat a velit fringilla, ut porta quam blandit. Morbi ornare tempus pharetra. Vivamus vehicula tellus ut consectetur malesuada. Donec lobortis rutrum orci. Phasellus ut semper augue, at vestibulum magna. Maecenas sodales malesuada feugiat. Aliquam est nibh, imperdiet eget vulputate eget, dictum a libero. Praesent molestie mi sit amet ante blandit convallis non et lacus. Nunc facilisis vulputate nulla, quis congue odio interdum at. Aliquam erat volutpat.Proin eu rutrum tellus. Praesent nisl magna, tempor pellentesque elit sed, cursus vulputate ligula. Nulla facilisi. Etiam bibendum iaculis nulla. Nulla vitae sem varius, condimentum tortor in, rhoncus neque. Nulla iaculis nisi sed nibh dapibus facilisis. Sed mollis risus odio, viverra volutpat nisi accumsan non. Duis aliquam ligula eu pretium sodales. Morbi dignissim placerat metus, sit amet pharetra velit lacinia non. Duis mattis tempus convallis. Cum sociis natoque penatibus et magnis dis parturient.</p>
		</div>

		<div class="viewIssueFooter">

			<div class="viewIssueFooterDetails">	

				<div class="viewIssueOwner">
					User sir Usealot
				</div>
				
				<div class="viewIssueDate">
					<small>Dummy Date 7.22.2014</small>
				</div>
		
			</div>	
			
			<div class="viewIssueFooterButtons">
			
				<button type="button" class="btn btn-primary" onclick="editIssue()">Update</button>
			
				<div class="new viewIssueState" >
					New 
				</div>
			
			</div>
			
			<div class="viewIssueFooterButtons hidden">

				<button type="button" class="btn btn-default">Cancel</button>
				<button type="button" class="btn btn-primary">Save</button>

			</div>
			<!-- <div class="opened viewIssueState"> -->
			<!-- 					Opened -->
			<!-- 				</div> -->


			<!-- 				<div class="testing viewIssueState"> -->
			<!-- 					In testing -->
			<!-- 				</div> -->

			<!-- 				<div class="closed viewIssueState"> -->
			<!-- 					Closed -->
			<!-- 				</div> -->

		</div>
		
		<!-- Pentru omul rau -->
		
		<div class="form-group">
				<label for="Title">Title</label>
				<sf:input type="text" class="form-control" id="Title"
					placeholder="Title goes here..." path="title" />
				<span class="error"></span>
		</div>
		
		<div class="label label-warning registerError">
				<c:forEach items="${errors}" var="error">
					<spring:message code="${error.getCode()}" />
				</c:forEach>
			</div>
		
	</div>

</div>