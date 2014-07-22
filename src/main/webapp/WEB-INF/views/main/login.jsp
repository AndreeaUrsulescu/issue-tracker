<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<script src="resources/assets/javascript/login-validator.js"></script>
<div class="container">
    <div class="loginForm"> 
        <form id="login-form" class="form-horizontal" method="POST" action='j_spring_security_check'>
    
          <div class="form-group" style="margin-top: 10px;">
            <label for="inputEmail" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
				<input type="text" class="form-control" id="username" placeholder="Username" name="j_username"/>
              <span class="error"></span> 
            </div>
          </div>
          <div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
              <input  type="password" class="form-control" id="password" placeholder="Password" name="j_password"/>
              <span class="error"></span> 
            </div>
            
          </div>
          <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
			<a  data-toggle="popover" data-placement="right" data-content="Parola" onclick="return validateForm();"><input class=" btn btn-primary myBtn"  type="submit" value="Sign in"/></a>
            </div>			  
          </div>
        </form>
    </div>
  </div>   