function updateIssue() {
	// se completeaza cu datele din formular + id-ul issue-ului
	var issue = {
			
	};
	
	//preia url-ul curent
	var url = window.location.origin + window.location.pathname;
		
	$.ajax({
		data: issue,
		dataType: "json",
		type: "POST",
		url: url,
		success: function(rsp) {
			if (rsp.issue == "success")
			{
				window.location = "http://localhost:8080/issue-tracker/issues";
			}
			else
			{
				// se completeaza inputurile cu valorile din rsp (rsp.issue.{nume-camp})
			}
		}
	});
}

function editIssue() {
	var url = window.location.origin + window.location.pathname;
	
	$.ajax({
		dataType: "json",
		type: "GET",
		url: url,
		success: function(rsp) {
			//se completeaza formularul cu datele din rsp.issue.{nume-camp}
		}
	});
}