// waiting for the page to be ready...


$(document).ready(function(){
	
	$("#Title").focus();
	
	$(".editError").hide();
	
	if( $(".editError").text().trim().length > 0){
		$(".editError").show();
	}
	
	function ValidateTitle()
	{
		var value=$("#issueTitle").val();
		
		var titleRegex= /^[a-zA-Z]+$/;
		
		$("#Title").parent().find("span").text(" ");

		if(value.length == 0){
			$("#Title").parent().find("span").text("Your Title can not be empty, an input is required.");
			return false;
		} else
		if(value.length<5 || value.length>50){
			$("#Title").parent().find("span").text("Your Title must be between 5-50 characters long.");
			return false;
		} else
		if(validTitle == null){
			$("#Title").parent().find("span").text("You can only use alphabetical characters.");
			return false;
		}
		return true;
	};

	$("#text").keyup(ValidateTitle);
});
	