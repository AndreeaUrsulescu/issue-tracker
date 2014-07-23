$(document).ready(function(){
	
	$("#username").focus();
	
	$(".registerError").hide();
	
	if( $(".registerError").text().trim().length > 0){
		$(".registerError").show();
	}
	
	function validateEmail(){
		var value  = $("#emailAdress").val();
		
		var email_regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		var validUsername = value.match(email_regex);
		
		$("#emailAdress").parent().find("span").text(" ");
		
		if(value.length == 0){
			$("#emailAdress").parent().find("span").text("Your email adress can not be empty, an input is required.");
			return false;
		} else
		if(validUsername == null){
			$("#emailAdress").parent().find("span").text("Invalid email adress");
			return false;
		}
	} else if (type == "-"  && count > 1 ) {
		count = count -1 ;
		$("#nextButton").css("visibility", "visible");	
		
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
		if(validUsername == null)
		{
			$("#username").parent().find("span").text("You can only use alphabetical characters.");
			return false;
		}
	}
	
	console.log(issuesListSize);
	$.ajax({
		url : "http://localhost:8080/issue-tracker/issues/page/" + count,
		type : "GET",

		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {

			document.getElementById("issuesContainer").innerHTML = "";

			for ( var index = 0; index <= response.length; index++) {

				var textarea = document.createElement("DIV");
				textarea.appendChild(document
						.createTextNode(response[index].title + "  "
								+ response[index].updateDate));
				document.getElementById("issuesContainer")
						.appendChild(textarea);
			}
		}
	
	//Pentru validarea titlului la createIssue
	function validateTitle(){
		$("#createTitle").parent().find("span").text("");
		var value  = $("#createTitle").val();	
		if((value.length == 0)||(value.length<3)||(value.length>50)){
			$("#createTitle").parent().find("span").text("A title between 3 and 50 characters is required");
			return false;
		}
		return true;
	};
	
	$("#createTitle").keyup(validateTitle);	
	$("#createIssueForm").submit(function(event){

	    var isValid = validateTitle();
	    
	    if (!isValid) {
	        event.preventDefault();
	    }
	});
	$("#username").keyup(validateUserName);
	$("#emailAdress").keyup(validateEmail);
	$("#password").keyup(validatePassword);
	$("#passwordConfirm").keyup(validateConfirm);
	$("#registerForm").submit(function(event){

	    var isValid = validateEmail() && validateUserName();
	    isValid = isValid && validatePassword() && validateConfirm();
	    
	    if (!isValid) {
	        event.preventDefault();
	    }
	});
	
});
