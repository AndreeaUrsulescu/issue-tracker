$(document).ready(function(){

	function validateTitle() {
		$("#createTitleSpan").text("");
		var value = $("#createTitle").val().trim();
		if ((value.length == 0) || (value.length < 5)
		|| (value.length > 50)) {
			$("#createTitleSpan").text("The title must have between 5 and 50 characters");
		return false;
		}
		return true;
	};
		
	$("#createTitle").focusout(validateTitle);
	$("#createIssueForm").submit(function(event) {
		var isValid = validateTitle();
		if (!isValid) {
			event.preventDefault();
		}
	});

});