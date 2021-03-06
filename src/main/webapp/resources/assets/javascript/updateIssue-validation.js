window.onload = function() {
	var element = document.getElementById("liUserName");
	var username = element.innerText;
	element = document.getElementById("assigneeLabel");
	var assignee = element.innerText;
	
	if (username.toLowerCase() !== assignee.toLowerCase()){
		document.getElementById("unassignButton").style.display = 'none';
	}
}


function ValidateTitle() {
	
	var title = $("#issueTitle");
	var span = title.parent().find("span");
	span.text("");

	var save = $("#send");
	
	save.prop('disabled',false); $("#send").addClass("validB");

	if (title.val().trim().length < 5 || title.val().length > 50) {
		span.text("Your title must have between 5 and 50 characters");
		save.prop('disabled',true); $("#send").removeClass("validB");
		return false;
	}
	return true;
};

function ValidateContent() {
	
	var save = $("#send");	
	
	if($(".editIssueContent").is(":visible")){
		save.prop('disabled',false); $("#send").addClass("validB");
	}
	return true;
};

function assignUser(){
	var filterData;
	var element = document.getElementById("liUserName");
	var userName = element.innerText;
	var userNameInput = $("#assigneeInput").val().trim();
	filterData = {
			userName : userNameInput
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
 			if (userNameInput.toLowerCase() === userName.toLowerCase()){
 				document.getElementById("unassignButton").style.display = "initial";
 			} else {
 				document.getElementById("unassignButton").style.display = "none";
 			}
 			document.getElementById("assigneeInput").style.display = "none";
 			document.getElementById("assignButton").style.display = "none";
 			document.getElementById("assigneeLabel").style.display = "inline";
 			document.getElementById("assigneeLabel").innerHTML = filterData.userName;
 			document.getElementById("assigneeBr").style.display = "none";
 		}
 	});
	
	$("#send").prop('disabled',false);
}

function unassignUser(){
	
	$.ajax({
 		url : window.location.origin + window.location.pathname + "/unassignUser" , // put some URL
 		type : "DELETE",
 		beforeSend : function(xhr) {
 			xhr.setRequestHeader("Accept", "application/json");
 			xhr.setRequestHeader("Content-Type", "application/json");
 		},
 		success : function(response) {
 			if (response.response === "success"){
	 			document.getElementById("assignButton").disabled=true;
	 			document.getElementById("unassignButton").style.display = "none";
	 			document.getElementById("assigneeInput").style.display = "initial";
	 			document.getElementById("assignButton").style.display = "initial";
	 			document.getElementById("assigneeLabel").style.display = "none";
	 			document.getElementById("assign").innerHTML = "Unassigned";
	 			document.getElementById("assigneeBr").style.display = "initial";
 			}
 		}
 	});
	
	$("#send").prop('disabled',false);
}
