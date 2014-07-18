$(document).ready(function(){
	
//	$(".form-group input").keyup(function (){
//		var value = $(this).val();
//		$(this).parent().find(".error").text(" ");
//		var field = $(this).parent().find("label").val();
//		console.log(field);
//		if(value.length == 0){
//			$(this).parent().find(".error").text(field + "'s text area can't be empty.");
//		}
//		console.log(value);
//		
////		if()
//	});
	
	$("#username").keyup(function(){
		var value  = $("#username").val();
		
		var usernameRegex = '/^[a-zA-Z]{5,12}$/';
		
		var validationRegex = value.match(usernameRegex);
		
		$("#username").parent().find("span").text(" ");
		
		if(value.length == 0){
			$("#username").parent().find("span").text("Your username can not be empty, an input is required.");
		}
		
		if((value.length!=0) && (value.length<5 || value.length>12)){

			$("#username").parent().find("span").text("Your username must be between 5-12 characters long.");
			
		} else{
			if(value.match(validationRegex)){
				$("#username").parent().find("span").text("You can only use alphabetical characters.");
			}
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
});