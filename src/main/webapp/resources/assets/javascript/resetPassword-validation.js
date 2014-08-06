$(document).ready(function() {
	function validatePassword() {
		var value = $("#password").val();
		$("#password").parent().find("span").text(" ");
		if (value.length < 5) {
			$("#password").parent().find("span").text("Your password must be at least 5 characters long.");
			return false;
		}
		return true;
	};

	function validateConfirm() {
		var password = $("#password").val();
		var confirm = $("#passwordConfirm").val();
		$("#passwordConfirm").parent().find("span").text(" ");
		if (password !== confirm) {
			$("#passwordConfirm").parent().find("span").text("Passwords do not match.");
			return false;
		}
		return true;
	};
	$("#password").focusout(validatePassword);
	$("#passwordConfirm").focusout(validateConfirm);
	$("#resetPasswordForm").submit(function(event) {
		var isValid = validatePassword()&& validateConfirm();
			if (!isValid) {
				event.preventDefault();
			}
		});
});	