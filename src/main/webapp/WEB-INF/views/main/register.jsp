<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container"> 	
	<div class="registerForm">
	
		<sf:form method="POST" role="form" modelAttribute="user">
			
			<div class="form-group">
			
				<label for="username">Username</label>
				<sf:input type="text" class="form-control" id="username" placeholder="Enter username" path="userName"/>
				<span class="error"></span>
				
			</div>
			
			<div class="form-group">
			
				<label  for="emailAdress">Email Adress</label>
				<sf:input type="email" class="form-control" id="emailAdress" placeholder="Enter email adress" path="email"/>
				<span class="error"></span>
				
			</div>
			
			<div class="form-group">
				
				<label  for="password">Password</label>
				<sf:input type="password" class="form-control" id="password" path="password"/>
				<span class="error"></span>
				
			</div>
				
			<div>
			
				<label  for="passwordConfirm">Confirm Password</label>
				<input type="password" class="form-control" id="passwordConfirm"></input>
				<span class="error"></span>
				
			</div>	
			
		<input type="submit" class="btn btn-primary center-block" value="Register" >
		
		</sf:form>
	
	</div>
</div>