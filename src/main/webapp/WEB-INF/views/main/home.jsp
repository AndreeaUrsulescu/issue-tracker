<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
    <div class="loginForm"> 
        <sf:form class="form-horizontal" role="form" method="POST" modelAttribute="user" action="<c:url value='j_spring_security_check' />">
    
          <div class="form-group" style="margin-top: 10px;">
            <label for="inputEmail" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
              <sf:input path="userName" type="text" class="form-control" id="inputEmail3" placeholder="Username" name="username"/>
            </div>
          </div>
          <div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
              <sf:input path="password" type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password"/>
            </div>
          </div>
          <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<a href="register" class="btn btn-primary myBtn ">Register</a>
				<a  class="btn btn-default myBtn" data-toggle="popover" data-placement="right" data-content="Parola" onclick="return validateForm();">Sign in</a>
            </div>			  
          </div>
        </sf:form>
    </div>
  <div>   