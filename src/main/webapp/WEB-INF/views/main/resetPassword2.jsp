<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="${pageContext.request.contextPath}/resources/assets/javascript/resetPassword-validation.js"></script>
			
<div class="container">
	<sf:form id="resetPasswordForm" method="POST" role="form"
		modelAttribute="userName">

		<div class="form-group">

				<label for="password">Password</label>
				<sf:input type="password" class="form-control" id="password"
					path="userName" />
				<span class="error"></span>

			</div>

			<div>

				<label for="passwordConfirm">Confirm Password</label> <input
					type="password" class="form-control" id="passwordConfirm"
					></input> <span class="error"></span>

			</div>
		<br>
		<input type="submit" id="submitButton"
			class="btn btn-primary center-block" value="Change password">
	</sf:form>
</div>