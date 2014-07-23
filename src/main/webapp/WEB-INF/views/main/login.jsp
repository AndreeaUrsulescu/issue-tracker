<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="resources/assets/javascript/login-validator.js"></script>
<div class="container" style="height: 790px;">
<div class="loginForm" style="margin-top: 300px; ">
<form id="login-form" class="form-horizontal" method="POST"
action='j_spring_security_check'>

<div class="form-group" style="margin-top: 10px;">
<label for="inputEmail" class="col-sm-2 control-label">Username</label>
<div class="col-sm-10">
<input type="text" class="form-control" id="username"
placeholder="Username" name="j_username" /> <span class="error"></span>
<!--for UI validation-->
</div>
</div>
<div class="form-group">
<label for="inputPassword" class="col-sm-2 control-label">Password</label>
<div class="col-sm-10">
<input type="password" class="form-control" id="password"
placeholder="Password" name="j_password" /> <span class="error"></span>
<!--for UI validation-->
</div>

</div>
<div class="form-group">
<div class="col-sm-offset-2 col-sm-10">
<a data-toggle="popover" data-placement="right"
data-content="Parola" onclick="return validateForm();"><input
class=" btn btn-primary myBtn" type="submit" value="Sign in" /></a>
</div>
</div>
</form>
<div class="label label-warning registerError">
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
</c:if>
</div>
</div>
</div>