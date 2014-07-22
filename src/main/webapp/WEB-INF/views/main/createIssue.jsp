<%-- 
    Document   : createIssue
    Created on : Jul 18, 2014, 1:25:41 PM
    Author     : lasoltanei
--%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<form class="form-horizontal" method="POST" action='j_spring_security_check'>
	<div id="createIssue">
		<div class="issueTitle">
		
			<label class="addedBy2">${date}</label>
			<img class="pinB2" src="resources/assets/images/paperclip2.png"/>
		</div>
		<div id="inIssue">

			   <label for="inputEmail" class="comic" require="required">Title</label>
			   <input type="text" class="form-control" id="createTitle" />
			   	<span class="error"></span>

			   <label for="inputPassword" class="comic">Content</label>
			   <textarea  maxlength="1000" id="createContent" cols="62" rows="14" placeholder="Your message here.." ></textarea>
			   <label class="addedBy">Added by ${user}</label>
		</div>
	</div>
			<div id="createBtn">
				<a ><input type="submit" value="Add issue" class="noBtn"/></a>
				<img class="pinB" src="resources/assets/images/pin.png"/>
			</div>
	</form>
  <div>   