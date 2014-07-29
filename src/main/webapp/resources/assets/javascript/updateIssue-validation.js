function ValidateTitle() {
	
	var title = $("#issueTitle");
	var span = title.parent().find("span");
	span.text("");

	var save = $("#send");
	
	save.prop('disabled',false);
	if ((title.val().trim().length == 0)) {
		span.text("Your title cannot be empty");
		save.prop('disabled',true);
		return false;
	}


	if ((title.val().trim().length < 5 || title.val().length > 50)) {
		span.text("Your title has to be between 5 and 50 characters");
		save.prop('disabled',true);
		return false;
	}
	
	if (!validateContent()) {
		save.prop('disabled',true);
		}
		
	return true;
};
function ValidateContent() {
	
	var content = $("#issueContent");
	var span = content.parent().find("span");
	var save = $("#send");
	span.text("");

	save.prop('disabled',false);


	if (content.val().trim().length > 1000) {
		span.text("Your content limit is 1000 characters");
		save.prop('disabled',true);
		return false;
	}
	
	if (!ValidateTitle()) {
		save.prop('disabled',true);
	}
	
	return true;
};

function assignUser(){
	var filterData;
	
	filterData = {
			userName : $("#assigneeInput").val().trim() // change it with input value
     	};
	$.ajax({
 		url : window.location.origin + window.location.pathname + "/assignUser" , // put some URL
 		type : "POST",
        data : JSON.stringify(filterData), 
 		beforeSend : function(xhr) {
 			xhr.setRequestHeader("Accept", "application/json");
 			xhr.setRequestHeader("Content-Type", "application/json");
 		},
 		success : function(response) {
 			$("#assigneeInput").val('');
 		}
 	});
}