$(document).ready(function(){

	$("#usernameX").focus();
	
	$(".registerError").hide();
	
	if( $(".registerError").text().trim().length > 0){
		$(".registerError").show();
	}
	
	function validateUserName(){
		var value  = $("#usernameX").val();
		
		var usernameRegex = /^[a-zA-Z]+$/;
		
		var validUsername = value.match(usernameRegex);
		
		$("#usernameX").parent().find("span").text(" ");
		
		if(value.length == 0){
			$("#usernameX").parent().find("span").text("Your username can not be empty, an input is required.");
			return false;
		} else
		if(value.length<5 || value.length>12){
			$("#usernameX").parent().find("span").text("Your username must be between 5-12 characters long.");
			return false;
		} else
		if(validUsername == null){
			$("#usernameX").parent().find("span").text("You can only use alphabetical characters.");
			return false;
		}
		return true;
	};
	
	function validatePassword(){
		var value = $("#passwordX").val();
		$("#passwordX").parent().find("span").text(" ");
		if(value.length == 0){
			$("#passwordX").parent().find("span").text("Your password can not be empty, an input is required.");
			return false;
		}
		if((value.length!=0) && value.length<5){
			$("#passwordX").parent().find("span").text("Your password must be at least 5 characters long.");
			return false;
		}
		return true;
	};
	

	$("#login-form").submit(function(event){

	    var isValid =  validateUserName() && validatePassword() ;

	    if (!isValid) {
	        event.preventDefault();
	    }
	});
});
