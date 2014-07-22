function updateIssue() {
	// se completeaza cu datele din formular (content, title, state)
	var issue = {
		'content' : 'continut',
		'title' : 'titlu',
		'state' : 1
	};
	
	//preia url-ul curent
	var url = window.location.origin + window.location.pathname;
		
	$.ajax({
		data: JSON.stringify(issue),
		dataType: "json",
		contentType: "application/json;charset=UTF-8",
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
				alert(rsp.issue.title);
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
			//alert(rsp.issue.title);
		}
	});
}

function addComment() {
	var url = window.location.origin + window.location.pathname;
	var comment = {
			//se completeaza cu datele din pagina (contentul)
		'content': 'bla'
	};
	
	$.ajax({
		data: JSON.stringify(comment),
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		type: "POST",
		url: url,
		succes: function(rsp) {
			for (var i = 0; i < rsp.comments.length; i++) {
				//rsp.comments[i].{proprietate} - accesul la campul unui comment
			}
		}
	});
}