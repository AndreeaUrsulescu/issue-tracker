$(document).ready(function(){

	$("#username").focus();
	
	$(".registerError").hide();
	
	if( $(".registerError").text().trim().length > 0){
		$(".registerError").show();
	}
	
	function validateUserName(){
		var value  = $("#username").val();
		
		var usernameRegex = /^[a-zA-Z]+$/;
		
		var validUsername = value.match(usernameRegex);
		
		$("#username").parent().find("span").text(" ");
		
		if(value.length == 0){
			$("#username").parent().find("span").text("Your username can not be empty, an input is required.");
			return false;
		} else
		if(value.length<5 || value.length>12){
			$("#username").parent().find("span").text("Your username must be between 5-12 characters long.");
			return false;
		} else
		if(validUsername == null){
			$("#username").parent().find("span").text("You can only use alphabetical characters.");
			return false;
		}
		return true;
	};
	
	function validatePassword(){
		var value = $("#password").val();
		$("#password").parent().find("span").text(" ");
		if(value.length == 0){
			$("#password").parent().find("span").text("Your password can not be empty, an input is required.");
			return false;
		}
		if((value.length!=0) && value.length<5){
			$("#password").parent().find("span").text("Your password must be at least 5 characters long.");
			return false;
		}
		return true;
	};
	
	$("#username").focusout(validateUserName);
	$("#password").focusout(validatePassword);
	
	$("#login-form").submit(function(event){

	    var isValid = validatePassword() && validateUserName();

	    if (!isValid) {
	        event.preventDefault();
	    }
	});
});
