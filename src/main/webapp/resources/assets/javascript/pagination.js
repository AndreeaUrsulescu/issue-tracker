
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
	ajaxForPagination(count);
}

function ajaxForPagination(page){
	
	$.ajax({
		url : "http://localhost:8080/issue-tracker/issues/page/" + page,
		type : "GET",

		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {

			document.getElementById("issues").innerHTML = "";
			
			for(var index = 0 ; index < response.length ; index ++ ){
				
				 var stickyNote = document.createElement("A");
				 var stickyNoteHref = "issues/issue/"+response[index].id;
				 stickyNote.setAttribute("href",stickyNoteHref);
				  
				 var stickyNoteContent = document.createElement("DIV");
				 stickyNoteContent.setAttribute("class", "issue");
				 stickyNoteContent.setAttribute("id","iss"+index);
				 
				 var title = document.createElement("DIV");
				 title.setAttribute("class", "border");
				 
				 var state =  document.createElement("LABEL");
				 state.setAttribute("class", "state");
				 state.appendChild(document.createTextNode(response[index].state));
				 
				 var date =  document.createElement("LABEL");
				 date.setAttribute("class", "date");
				 date.appendChild(document.createTextNode(response[index].updateDate));
				 
				 title.appendChild(state);
				 title.appendChild(date);
				 
				 
				 var content = document.createElement("DIV");
				 content.setAttribute("class", "content");
				 
				 var contentTitle = document.createElement("H4");
				 contentTitle.setAttribute("class", "title");
				 contentTitle.appendChild(document.createTextNode(response[index].title));
				 
				 var paragraf = document.createElement("P");
				 paragraf.appendChild(document.createTextNode(response[index].content));
				 
				 content.appendChild(contentTitle);
				 content.appendChild(paragraf);
				 
				 var owner = document.createElement("LABEL");
				 owner.setAttribute("class", "owner");
				 owner.appendChild(document.createTextNode("Updated by "+ response[index].owner));
				 
				 stickyNoteContent.appendChild(title);
				 stickyNoteContent.appendChild(content);
				 stickyNoteContent.appendChild(owner);
				 
				 stickyNote.appendChild(stickyNoteContent);
				 document.getElementById("issues").appendChild(stickyNote);
			}
		}
	});
}

window.onload = function () {
	
	    numberOfIssues = $('#numberOfIssues').text();
	    issuesPerPage = $('#issuesPerPage').text();
	    
	    if ((numberOfIssues - issuesPerPage) <= 0 )
	    	$("#nextButton").css("visibility", "hidden");
	    
	    $("#previousButton").css("visibility", "hidden");	
};
