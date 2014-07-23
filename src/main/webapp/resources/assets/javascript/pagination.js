
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

			document.getElementById("issuesContainer").innerHTML = "";

			for ( var index = 0; index <= response.length; index++) {

				var textarea = document.createElement("DIV");
				textarea.appendChild(document
						.createTextNode(response[index].title + "  "
								+ response[index].updateDate));
				document.getElementById("issuesContainer")
						.appendChild(textarea);
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
