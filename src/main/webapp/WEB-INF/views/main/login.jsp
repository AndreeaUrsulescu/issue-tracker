<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
    <div class="loginForm"> 
        <form class="form-horizontal" method="POST" action='j_spring_security_check'>
    
          <div class="form-group" style="margin-top: 10px;">
            <label for="inputEmail" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputEmail3" placeholder="Username" name="j_username"/>
            </div>
          </div>
          <div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
              <input  type="password" class="form-control" id="inputPassword3" placeholder="Password" name="j_password"/>
            </div>
          </div>
          <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
			<a class="btn btn-default myBtn" data-toggle="popover" data-placement="right" data-content="Parola" onclick="return validateForm();"><input type="submit" value="Sign in"/></a>
            </div>			  
          </div>
        </form>
    </div>
  </div>   