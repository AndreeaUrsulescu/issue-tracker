<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="resources/assets/javascript/login-validator.js"></script>
<div class="container" style="height: 780px;">
	<img class="loginImg"
		src="${pageContext.request.contextPath}/resources/assets/images/4login.png" />
	<div class="loginForm" style="">
		<form id="login-form" class="form-horizontal" method="POST"
			action='j_spring_security_check'>

			<div class="form-group" style="margin-top: 10px;">
				<label for="inputEmail" class="col-sm-2 control-label">Username</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="usernameX"
						placeholder="Username" name="j_username" /> <span class="error"></span>
					<!--for UI validation-->
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="passwordX"
						placeholder="Password" name="j_password" /> <span class="error"></span>
					<!--for UI validation-->
				</div>

			</div>
			<div class="form-group">
				<button class='btn btn-primary myBtn center-block' type="submit">Sign in</button>
			</div>
			<div class="label label-primary registerError center-block">
				<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
	<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
				</c:if>
			</div>
		</form>
		
	</div>
	<!--for resetPassword-->
	<div class="center-block resetPasswordLink" ><a href="${pageContext.request.contextPath}/user/resetPasswordForm" >Forgot your password?</a></div>
</div>