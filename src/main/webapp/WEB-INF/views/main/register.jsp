<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="resources/assets/javascript/register-validation.js"></script>
<div class="container registerContainer" style="margin-left: -15px;">

	<div class="registerForm" style="margin-top: 150px;">

		<sf:form id="registerForm" method="POST" role="form"
			modelAttribute="user">

			<div class="form-group">
				<label for="username">Username</label>
				<sf:input type="text" class="form-control" id="username"
					placeholder="Enter username" path="userName" />
				<span class="error"></span>

			</div>

			<div class="form-group">

				<label for="emailAdress">Email Adress</label>
				<sf:input type="email" class="form-control" id="emailAdress"
					placeholder="Enter email adress" path="email" />
				<span class="error"></span>

			</div>

			<div class="form-group">

				<label for="password">Password</label>
				<sf:input type="password" class="form-control" id="password"
					path="password" />
				<span class="error"></span>

			</div>

			<div>

				<label for="passwordConfirm">Confirm Password</label> <input
					type="password" class="form-control" id="passwordConfirm"
					></input> <span class="error"></span>

			</div>

			<input type="submit" id="submitButton"
				class="btn btn-primary center-block" value="Register">

			<!-- Here happens the magic -->
			<div class="label label-warning registerError">
				<c:forEach items="${errors}" var="error">
					<spring:message code="${error.getCode()}" />
				</c:forEach>
			</div>

		</sf:form>
	</div>
	<div id="niceAdd" style="margin-left: 35%; margin-top: 20px;">
		<p id="promo1" class="promo" style="">1. Create an account</p>
		<p id="promo2" class="promo">2. Sign in to your page</p>
		<p id="promo3" class="promo">3. Share and fulfill your issues</p>
		<p id="promo4" class="promo">That simple :)</p>
	</div>
</div>