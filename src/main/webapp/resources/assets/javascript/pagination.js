
window.onload = function () {
	
	$("#nextButton").on( "click",issuePagination('${plus}','${listLength}','${itemsPerPage}'));
	$("#previousButton").on( "click",issuePagination('${minus}','${listLength}','${itemsPerPage}'));
	
	numberOfIssues = $('#numberOfIssues').text();
	issuesPerPage = $('#issuesPerPage').text();
	    
    if ((numberOfIssues - issuesPerPage) <= 0 )
	   	$("#nextButton").css("visibility", "hidden");
	    
     $("#previousButton").css("visibility", "hidden");	
};

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
	 document.getElementById("pageNumber").innerHTML = count;
	ajaxForPagination(count);
}

var countOnSort = 1 ;

function sortIssues(issuesListSize,issuesPerPage,sortCriteria){
     countOnSort = 1 ; 
     
     $("#nextButton").on( "click",sortIssuesPagination('+',issuesListSize,issuesPerPage));
 	 $("#previousButton").on( "click",sortIssuesPagination('-',issuesListSize,issuesPerPage));
 	 
     if ((issuesListSize - issuesPerPage) <= 0 )
 	   	$("#nextButton").css("visibility", "hidden");
 	    
      $("#previousButton").css("visibility", "hidden");	
      
      
}

function ajaxForPagination(page){
	
	
	$.ajax({
		url : "issues/page/" + page,
		type : "GET",
        
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {
			parsingAjaxResponse(response);
			
			}
	});
}

function parsingAjaxResponse(response){
	
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
		 var paragrafContent;
		 
		 if ( (response[index].content).length > 150 ){
			 paragrafContent = (response[index].content).substring(0,145) + "...";
			 }
		 else paragrafContent = response[index].content;
		 
		 paragraf.appendChild(document.createTextNode(paragrafContent));
		 
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
