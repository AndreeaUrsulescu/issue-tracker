<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
    <div class="loginForm"> 
        <form class="form-horizontal" method="POST" action='j_spring_security_check'>
    
          <div class="form-group" style="margin-top: 10px;">
            <label for="inputEmail" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="username" placeholder="Username" name="j_username"/>
              <span class="error"></span> <!--for UI validation-->
            </div>
          </div>
          <div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
              <input  type="password" class="form-control" id="password" placeholder="Password" name="j_password"/>
              <span class="error"></span> <!--for UI validation-->
            </div>
            
          </div>
          <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
			<input type="submit" value="Sign in"/>			
            </div>			  
          </div>
        </form>
    </div>
  </div>   