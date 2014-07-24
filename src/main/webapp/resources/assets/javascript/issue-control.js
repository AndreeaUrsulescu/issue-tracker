$(document).ready(function(){
    function updateIssue() {
    	// se completeaza cu datele din formular (content, title, state)
    	var issue = {
    		'content' : $("#issueContent").val(),
    		'title' : $("#issueTitle").val(),
    		'state' : $('.current').attr("id")
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
    				//reincarca pagina curenta
    				location.reload();
    			}
    			else
    			{
    				// se completeaza inputurile cu valorile din rsp (rsp.issue.{nume-camp})
					$('#issueTitle').val(rsp.issue.title);
					var id ='#'+rsp.issue.state;
					$('div').removeClass('current');
					$(id).addClass('current');
					$('#issueContent').val(rsp.issue.content);
    			}
    		}
    	});
    }

    function editIssue() {
    	var url = window.location.origin + window.location.pathname + "/api";
    	$.ajax({
    		dataType: "json",
    		type: "GET",
    		url: url,
    		success: function(rsp) {
    			$("#issueTitle").val(rsp.issue.title);
    			$("#issueContent").val(rsp.issue.content);
				var id ='#'+rsp.issue.state; 
				$(id).addClass('current');
//    			$("#issue-states:nth-child("+rsp.issue.state+")").addClass('current');
    		}
    	});
    	$(".editIssueContent").show();
    	$(".viewIssue").hide();
    }
    
    $(".viewIssueTitleEdit").focus();
    $("#edit").click(editIssue);
    $("#send").click(updateIssue);

	$(".editIssueContent").hide();
	
    $("#reset").click(function(){
        location.reload();
        //reload page in order to get the most recent "issue"
    });

    //for each subdiv
    $('#issue-states > .viewIssueState').on('click',function(){
       $('div').removeClass('current');
       $(this).addClass('current');
    

     //set current active state
       $("#send").prop('disabled',false);
       
       //issue title is valid?
      	if (($("#issueTitle").val().length < 5)) {
      		$("#issueTitle").parent().find("span").text("Your title cannot be empty");
      		$("#send").prop('disabled',true);
   		return false;
      	}
      	

       
    });
    
});
