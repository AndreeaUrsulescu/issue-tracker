<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	tinymce
			.init({
				selector : "textarea",
				theme : "modern",
				height : "180",
				resize : false,
				plugins : [
						"advlist autolink lists link charmap print preview hr pagebreak",
						"searchreplace wordcount visualblocks visualchars code fullscreen",
						"insertdatetime  nonbreaking save contextmenu directionality",
						"emoticons template paste textcolor colorpicker textpattern" ],
				toolbar1 : "insertfile undo redo | styleselect | bold italic | link | forecolor backcolor emoticons",

				image_advtab : true,
				templates : [ {
					title : 'Test template 1',
					content : 'Test 1'
				}, {
					title : 'Test template 2',
					content : 'Test 2'
				} ]
			});
</script>

<script type="text/javascript">
window.onload=function(){
	$('#createTitle').text('');
	document.getElementById("createTitle").focus();
	$('#createContent').text('');}
</script>

<div class="container" style="height: 785px;">
	<sf:form id="createIssueForm" class="form-horizontal" method="POST"
		action='createIssue' modelAttribute="issue">
		<div id="createIssue" style="background-color: white;">
			<div class="issueTitle">

				<label class="addedBy2">${date}</label> <img class="pinB2"
					src="${pageContext.request.contextPath}/resources/assets/images/paperclip2.png" />
			</div>
			<div id="inIssue">
				
				<label for=createTitle class="comic right-space">Title</label>
				<span id="createTitleSpan" class="error"></span>
				<sf:input type="text" class="form-control" id="createTitle" 
					path="title" />


				<label for="inputPassword" class="comic">Content</label>
				<sf:textarea id="createContent" cols="62" rows="14"
					placeholder="Your message here.." path="content"></sf:textarea>
				<label class="addedBy">Added by ${user}</label>
			</div>
		</div>
		<div id="backBtn">
			<a href="${pageContext.request.contextPath}/issues">
				<img class="pinB"
					src="${pageContext.request.contextPath}/resources/assets/images/large2.jpg" />
				<label class="backLabel">Go back</label>
			</a>
		</div>
		<div id="createBtn">
			<a><input type="submit" value="Add issue" class="noBtn" /></a> <img
				class="pinB"
				src="${pageContext.request.contextPath}/resources/assets/images/pin.png" />
		</div>
	</sf:form>
</div>

