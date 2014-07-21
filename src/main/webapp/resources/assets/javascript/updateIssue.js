function updateIssue() {
	// se completeaza cu datele din formular + id-ul issue-ului
	var issue = {
			
	};
	
	var url = "http://localhost:8080/issue-tracker/issues/issue/" + issue.id;
		
	$.ajax({
		data: issue,
		dataType: "json",
		type: "POST",
		url: url,
		success: function(rsp) {
			//alert(rsp.issue);
			if (rsp.issue == "success")
			{
				window.location = "http://localhost:8080/issue-tracker/issues";
			}
			else
			{
				// se completeaza inputurile cu valorile din rsp
			}
		}
	});
}