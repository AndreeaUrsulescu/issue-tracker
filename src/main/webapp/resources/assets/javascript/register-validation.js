$(document).ready(function() {
	$("#promo1").css("visibility", "visible");
	$("#promo1").animate({  marginTop: '10px' },1000,function(){
		$("#promo2").css("visibility", "visible");
			$("#promo2").animate({ marginTop: '40px' },1000,function(){
			$("#promo3").css("visibility", "visible");
				$("#promo3").animate({  marginTop: '70px' },1000, function(){
				$("#promo4").fadeIn(2000);
			});
		});
	});
	$("#username").focus();
	$(".registerError").hide();
	if ($(".registerError").text().trim().length > 0) {
		$(".registerError").show();
	}
	function validateEmail() {
		var value = $("#emailAdress").val();
		var email_regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var validEmailAdress= value.match(email_regex);
		$("#emailAdress").parent().find("span").text(" ");
		if (validEmailAdress == null) {
			$("#emailAdress").parent().find("span").text("Email adress is not valid");
			return false;
		}
		return true;
	};
	
	function validateUserName() {
		var value = $("#username").val();
		var usernameRegex = /^[a-zA-Z]{5,12}$/;
		var validUsername = value.match(usernameRegex);
		$("#username").parent().find("span").text(" ");
		if (validUsername == null) {
			$("#username").parent()	.find("span").text("The username must have between 5 and 12 letters");
			return false;
		}
		return true;
	}
	;
	
	function validatePassword() {
		var value = $("#password").val();
		$("#password").parent().find("span").text(" ");
		if (value.length < 5) {
			$("#password").parent().find("span").text("Your password must have at least 5 characters");
			return false;
		}
		return true;
	};
	
	function validateConfirm() {
		var password = $("#password").val();
		var confirm = $("#passwordConfirm").val();
		$("#passwordConfirm").parent().find("span").text(" ");
		if (password !== confirm) {
			$("#passwordConfirm").parent().find("span").text("The password does not match.");
			return false;
		}
		return true;
	};
	$("#createTitleSpan").focus();
	
	$("#username").focusout(validateUserName);
	$("#emailAdress").focusout(validateEmail);
	$("#password").focusout(validatePassword);
	$("#passwordConfirm").focusout(validateConfirm);
	$("#registerForm").submit(function(event) {
		var isValid = validateEmail()&& validateUserName();
		isValid = isValid && validatePassword() && validateConfirm();
		if (!isValid) {
			event.preventDefault();
		}
	});
});