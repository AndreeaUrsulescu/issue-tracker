<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container" >
	<sf:form id="createIssueForm" class="form-horizontal" method="POST" action='createIssue'
	modelAttribute="issue">
	<div id="createIssue">
		<div class="issueTitle">
		
			<label class="addedBy2">${date}</label>
			<img class="pinB2" src="${pageContext.request.contextPath}/resources/assets/images/paperclip2.png"/>
		</div>
		<div id="inIssue">

			   <label for="inputEmail" class="comic" require="required">Title</label> <span style="margin-left:90px;" class="error"></span>
			   <sf:input type="text" class="form-control" id="createTitle" path="title"/>
			

			   <label for="inputPassword" class="comic">Content</label>
			   <sf:textarea  maxlength="1000" id="createContent" cols="62" rows="14" placeholder="Your message here.." path="content"></sf:textarea>
			   <label class="addedBy">Added by ${user}</label>
		</div>
	</div>
		<a onclick="window.location='./'; ">
		<div id="backBtn">
 			<img class="pinB" src="${pageContext.request.contextPath}/resources/assets/images/large2.jpg"/>
 			<label class="backLabel"> Go back</label>
 		</div>
 		</a>
			<div id="createBtn">
				<a ><input type="submit" value="Add issue" class="noBtn"/></a>
				<img class="pinB" src="${pageContext.request.contextPath}/resources/assets/images/pin.png"/>
			</div>
	</sf:form>
  </div>   

