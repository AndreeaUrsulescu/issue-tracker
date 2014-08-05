<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<sf:form id="resetPasswordForm" method="POST" role="form"
		modelAttribute="userName">

		<label for="username">Username</label>
		<sf:input type="text" class="form-control" id="username"
			placeholder="Enter username" path="userName" />
		<span class="error"></span>
		<br>
		<input type="submit" id="submitButton"
			class="btn btn-primary center-block" value="Reset Password">
	</sf:form>
</div>