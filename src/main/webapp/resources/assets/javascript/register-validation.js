$(document)
		.ready(
				function() {
					$(".registerError").hide();

					if ($(".registerError").text().trim().length > 0) {
						$(".registerError").show();
					}

					function validateEmail() {
						var value = $("#emailAdress").val();

						var email_regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

						var validUsername = value.match(email_regex);

						$("#emailAdress").parent().find("span").text(" ");

						if (value.length == 0) {
							$("#emailAdress")
									.parent()
									.find("span")
									.text(
											"Your email adress can not be empty, an input is required.");
							return false;
						} else if (validUsername == null) {
							$("#emailAdress").parent().find("span").text(
									"Invalid email adress");
							return false;
						}
						return true;
					}
					;

					function validateUserName() {
						var value = $("#username").val();

						var usernameRegex = /^[a-zA-Z]+$/;

						var validUsername = value.match(usernameRegex);

						$("#username").parent().find("span").text(" ");

						if (value.length == 0) {
							$("#username")
									.parent()
									.find("span")
									.text(
											"Your username can not be empty, an input is required.");
							return false;
						} else if (value.length < 5 || value.length > 12) {
							$("#username")
									.parent()
									.find("span")
									.text(
											"Your username must be between 5-12 characters long.");
							return false;
						} else if (validUsername == null) {
							$("#username")
									.parent()
									.find("span")
									.text(
											"You can only use alphabetical characters.");
							return false;
						}
						return true;
					}
					;

					function validatePassword() {
						var value = $("#password").val();
						$("#password").parent().find("span").text(" ");
						if (value.length == 0) {
							$("#password")
									.parent()
									.find("span")
									.text(
											"Your password can not be empty, an input is required.");
							return false;
						}
						if ((value.length != 0) && value.length < 5) {
							$("#password")
									.parent()
									.find("span")
									.text(
											"Your password must be at least 5 characters long.");
							return false;
						}
						return true;
					}
					;

					function validateConfirm() {
						var password = $("#password").val();
						var confirm = $("#passwordConfirm").val();
						$("#passwordConfirm").parent().find("span").text(" ");
						if (password !== confirm) {
							$("#passwordConfirm").parent().find("span").text(
									"Passwords do not match.");
							return false;
						}
						return true;
					}
					;
					$("#username").keyup(validateUserName);
					$("#emailAdress").keyup(validateEmail);
					$("#password").keyup(validatePassword);
					$("#passwordConfirm").keyup(validateConfirm);

					$("#registerForm").submit(
							function(event) {

								var isValid = validateEmail()
										&& validateUserName();
								isValid = isValid && validatePassword()
										&& validateConfirm();

								if (!isValid) {
									event.preventDefault();
								}
							});

				});


var count  = 1;

function issuePagination(type,issuesListSize,issuesPerPage) {

	if (type == "+" && (issuesListSize - ( count*issuesPerPage )) > 0 ) {
		count = count + 1 ;
		if ( (issuesListSize - ( count*issuesPerPage )) <= 0 ){
			$("#nextButton").css("visibility", "hidden");	
			$("#previousButton").css("visibility", "visible");
		}
		else {
			$("#nextButton").css("visibility", "visible");	
			$("#previousButton").css("visibility", "visible");
			
		}
	} else if (type == "-"  && count > 1 ) {
		count = count -1 ;
		$("#nextButton").css("visibility", "visible");	
		
		if(count == 1){
			$("#previousButton").css("visibility", "hidden");	
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
	});

}
