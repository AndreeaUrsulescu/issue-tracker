<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
    <div class="loginForm"> 
        <sf:form class="form-horizontal" role="form" method="POST" modelAttribute="user">
    
          <div class="form-group" style="margin-top: 10px;">
            <label for="inputEmail3" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
              <sf:input path="userName" type="text" class="form-control" id="inputEmail3" placeholder="Username"/>
            </div>
          </div>
          <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
              <sf:input path="password" type="password" class="form-control" id="inputPassword3" placeholder="Password"/>
            </div>
          </div>
          <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<a href="register" class="btn btn-primary myBtn ">Register</a>
				<a  class="btn btn-default myBtn" data-toggle="popover" data-placement="right" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">Sign in</a>
            </div>			  
          </div>
        </sf:form>
    </div>
  <div>