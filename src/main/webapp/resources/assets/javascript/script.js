$(document).ready(function(){
	
	$("#emailAdress").keyup(function(){
		var value  = $("#emailAdress").val();
		
		var email_regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		var validUsername = value.match(email_regex);
		
		$("#emailAdress").parent().find("span").text(" ");
		
		if(value.length == 0){
			$("#emailAdress").parent().find("span").text("Your email adress can not be empty, an input is required.");
		} else
		if(validUsername == null){
			$("#emailAdress").parent().find("span").text("Invalid email adress");
		}
	});
	
	$("#username").keyup(function(){
		var value  = $("#username").val();
		
		var usernameRegex = /^[a-zA-Z]+$/;
		
		var validUsername = value.match(usernameRegex);
		
		$("#username").parent().find("span").text(" ");
		
		if(value.length == 0){
			$("#username").parent().find("span").text("Your username can not be empty, an input is required.");
		} else
		if(value.length<5 || value.length>12){
			$("#username").parent().find("span").text("Your username must be between 5-12 characters long.");
		} else
		if(validUsername == null){
			$("#username").parent().find("span").text("You can only use alphabetical characters.");
		}
	});
	
	$("#password").keyup(function(){
		
		var value = $("#password").val();
		
		$("#password").parent().find("span").text(" ");
		
		if(value.length == 0){
			
			$("#password").parent().find("span").text("Your password can not be empty, an input is required.");
			
		}
		
		if((value.length!=0) && value.length<5){
			$("#password").parent().find("span").text("Your password must be at least 5 characters long.");
		}
		
	});
	
	$("#passwordConfirm").keyup(function(){
		
		var password = $("#password").val();
		var confirm = $("#passwordConfirm").val();
		
		$("#passwordConfirm").parent().find("span").text(" ");
		
		if(password !== confirm){
			
			$("#passwordConfirm").parent().find("span").text("Passwords do not match.");
			
		}
	});
});