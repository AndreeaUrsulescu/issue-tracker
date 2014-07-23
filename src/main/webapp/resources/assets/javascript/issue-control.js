$(document).ready(function(){
    function updateIssue() {
    	// se completeaza cu datele din formular (content, title, state)
    	var issue = {
    		'content' : $("#issueContent").val(),
    		'title' : $("#issueTitle").val(),
    		'state' : $('div.current').index()
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
    			$("#issueTitle").val(rsp.issue.title);
    			$("#issueContent").val(rsp.issue.content);
    			$("#issue-states:nth-child("+rsp.issue.state+")").addClass('current');
    		}
    	});
    	$(".editIssueContent").show();
    	$(".viewIssue").hide();
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
    });
    
});
