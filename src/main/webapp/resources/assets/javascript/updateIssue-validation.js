window.onload = function() {
	var element = document.getElementById("liUserName");
	var username = element.innerText;
	element = document.getElementById("assigneeLabel");
	var assignee = element.innerText;
	
	if (username !== assignee){
		document.getElementById("unassignButton").style.display = 'none';
	}
}


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
	
	
		
	return true;
};

function ValidateContent() {
	
	var save = $("#send");	
	
	//if($(".editIssueContent").is(":visible")){
		save.prop('disabled',false);
	//}
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
 			document.getElementById("unassignButton").style.display = "initial";
 			document.getElementById("assigneeInput").style.display = "none";
 			document.getElementById("assignButton").style.display = "none";
 			document.getElementById("assigneeLabel").style.display = "inline";
 			document.getElementById("assigneeLabel").innerHTML = filterData.userName;
 			document.getElementById("assigneeBr").style.display = "none";
 		}
 	});
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
}
