
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
		
	///////////////////////////////////////////////////////////////////////////////////
	
	 $("#issues").animate({  marginLeft: '-1800px' },0,function(){
		   $("#issues").fadeOut(1);
		   $("#issues").animate({  marginLeft: '1800px' },0,function(){
		   $("#issues").fadeIn(1);
		   $("#issues").animate({  marginLeft: '80px' },1000);
	   });
	 });

	///////////////////////////////////////////////////////////////////////////////////
	
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

	///////////////////////////////////////////////////////////////////////////////////
		
	
	 $("#issues").animate({  marginLeft: '1800px' },0,function(){
		   $("#issues").fadeOut(1);
		   $("#issues").animate({  marginLeft: '-1800px' },0,function(){
		   $("#issues").fadeIn(1);
		   $("#issues").animate({  marginLeft: '80px' },1000);
	   });
	 });

	
	///////////////////////////////////////////////////////////////////////////////////
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

// TODO: if needed change val() with text()
function searchIssues(){
     countOnSort = 1 ; 

     var searchCriteria = $("#selectS").val().trim(); 
     var input ; 
     var state ; 
     var filterData ;
     
     if (searchCriteria == "state") {
    	 
      state = $("#selectT").val();
      filterData = {
    		 searchCriteria : searchCriteria ,
    		 state : state,
    		 pageNumber : 1,
    		 sortCriteria : $("#criteria").val().trim(),
    		 sortType : $("#order").val().trim()
      	};
     }
     else {
    	 input = $("#searchField").val();
    	 filterData = {
        		 searchCriteria : searchCriteria ,
        		 input : input,
        		 pageNumber : 1,
        		 sortCriteria : $("#criteria").val().trim(),
        		 sortType : $("#order").val().trim()
          	};
     }
     
     if (searchCriteria == "state") {
    	 
      state = $("#selectT").val().trim();
      filterData = {
    		 searchCriteria : searchCriteria ,
    		 state : state,
    		 pageNumber : 1,
    		 sortCriteria : $("#criteria").val().trim(),
    		 sortType : $("#order").val().trim()
      	};
     }
     else {
    	 input = $("#searchField").val().trim();
    	 filterData = {
        		 searchCriteria : searchCriteria ,
        		 input : input,
        		 pageNumber : 1,
        		 sortCriteria : $("#criteria").val().trim(),
        		 sortType : $("#order").val().trim()
          	};
     }
     
     $.ajax({
 		url : "issues/searchBy" , // put some URL
 		type : "GET",
        data : filterData, 
 		beforeSend : function(xhr) {
 			xhr.setRequestHeader("Accept", "application/json");
 			xhr.setRequestHeader("Content-Type", "application/json");
 		},
 		success : function(response) {
 			
 			document.getElementById("nextButton").setAttribute("onclick","sortIssuesPagination('+',"+response.listLength+","+response.issuesPerPage+");");
 			document.getElementById("previousButton").setAttribute("onclick","sortIssuesPagination('-',"+response.listLength+","+response.issuesPerPage+");");
 			
 			$("#nextButton").css("visibility", "visible");
 			if ((response.listLength - response.issuesPerPage) <= 0 )
 			   	$("#nextButton").css("visibility", "hidden");
 			    
 		     $("#previousButton").css("visibility", "hidden");	
 		     
 		    document.getElementById("pageNumber").innerHTML = 1;
 	        document.getElementById("total").innerHTML = "/"+Math.ceil((response.listLength/response.issuesPerPage));
 		     
 		     parsingAjaxResponse(response.issuesList);
 			}
 	});
}

function sortIssuesPagination(type,issuesListSize,issuesPerPage){
	if (type == "+" && (issuesListSize - ( countOnSort*issuesPerPage )) > 0 ) {

	///////////////////////////////////////////////////////////////////////////////////
	
	 $("#issues").animate({  marginLeft: '-1800px' },0,function(){
		   $("#issues").fadeOut(1);
		   $("#issues").animate({  marginLeft: '1800px' },0,function(){
		   $("#issues").fadeIn(1);
		   $("#issues").animate({  marginLeft: '80px' },1000);
	   });
	 });

	///////////////////////////////////////////////////////////////////////////////////	
	
		countOnSort = countOnSort + 1 ;
		if ( (issuesListSize - ( countOnSort*issuesPerPage )) <= 0 ){
			$("#nextButton").css("visibility", "hidden");	
			$("#previousButton").css("visibility", "visible");
		}
		else {
			$("#nextButton").css("visibility", "visible");	
			$("#previousButton").css("visibility", "visible");
			
		}
	} else if (type == "-"  && countOnSort > 1 ) {
	
	///////////////////////////////////////////////////////////////////////////////////
		
	
	 $("#issues").animate({  marginLeft: '1800px' },0,function(){
		   $("#issues").fadeOut(1);
		   $("#issues").animate({  marginLeft: '-1800px' },0,function(){
		   $("#issues").fadeIn(1);
		   $("#issues").animate({  marginLeft: '80px' },1000);
	   });
	 });

	
	///////////////////////////////////////////////////////////////////////////////////
	
		countOnSort = countOnSort -1 ;
		$("#nextButton").css("visibility", "visible");	
		
		if(countOnSort == 1){
			$("#previousButton").css("visibility", "hidden");	
		}
	}
	 document.getElementById("pageNumber").innerHTML = countOnSort;
     document.getElementById("total").innerHTML ="/"+ Math.ceil((issuesListSize/issuesPerPage));
	 ajaxForSearchPagination(countOnSort);
	 
}

function ajaxForSearchPagination(page){
	
	var searchCriteria = $("#selectS").val(); 
    var input ;  
    var state ; 
    var filterData ;
    
    if (searchCriteria == "state") {
   	 
        state = $("#selectT").val();
        filterData = {
      		 searchCriteria : searchCriteria ,
      		 state : state,
      		 pageNumber : page,
      		 sortCriteria : $("#criteria").text(),
      		 sortType : $("#order").text()
        	};
       }
       else {
      	 input = $("#searchField").val();
      	 filterData = {
          		 searchCriteria : searchCriteria ,
          		 input : input,
          		 pageNumber : page,
          		 sortCriteria : $("#criteria").text(),
          		 sortType : $("#order").text()
            	};
       }
     
	$.ajax({
 		url : "issues/searchBy" , 
 		type : "GET",
        data : filterData, 
 		beforeSend : function(xhr) {
 			xhr.setRequestHeader("Accept", "application/json");
 			xhr.setRequestHeader("Content-Type", "application/json");
 		},
 		success : function(response) {
 			 
 			parsingAjaxResponse(response.issuesList);
 			}
 	});
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
		 
		 if(response[index].state == "New") 
			 stickyNoteContent.setAttribute("class","issue iss"+6); 
		 if(response[index].state == "Opened") 
			 stickyNoteContent.setAttribute("class","issue iss"+2); 
		 if(response[index].state == "Testing") 
			 stickyNoteContent.setAttribute("class","issue iss"+0); 
		 if(response[index].state == "Closed") 
			 stickyNoteContent.setAttribute("class","issue iss"+5);
		 

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

