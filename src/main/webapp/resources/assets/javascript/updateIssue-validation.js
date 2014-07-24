function ValidateTitle() {
	
	var title = $("#issueTitle");
	var span = title.parent().find("span");
	span.text("");
	//var titleRegex = /^[a-zA-Z0-9]+$/;
	//var validTitle = title.val().match(titleRegex);
	var save = $("#send");
	
	save.prop('disabled',false);
	if ((title.val().trim().length == 0)) {
		span.text("Your title cannot be empty");
		save.prop('disabled',true);
		return false;
	}

/*	if (validTitle == null) {
		span.text("Only alpha-numeric characters please...");
		save.prop('disabled',true);
		return false;
	}*/

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
	//var contentRegex = /^[a-zA-Z0-9]+$/;
	//var validcontent = content.val().match(contentRegex);
	save.prop('disabled',false);
	
//	if ((content.val().length == 0)) {
//		span.text("Your content cannot be empty");
//		save.prop('disabled',true);
//		return false;
//	}

/*	if (validcontent == null) {
		span.text("Only alpha-numeric characters please...");
		save.prop('disabled',true);
		return false;
	}*/

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