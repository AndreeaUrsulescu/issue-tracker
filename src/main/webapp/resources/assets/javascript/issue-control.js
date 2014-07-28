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
    	$("#newComment").hide();
    	$("#label-editor").show();
    	
    }
    $("#label-editor").hide();
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
      	if (($("#issueTitle").val().trim().length < 5)) {
      		$("#issueTitle").parent().find("span").text("Your title cannot be empty");
      		$("#send").prop('disabled',true);
   		return false;
      	}
    });
    /** Label validation and insert **/
    function validateLabel(){
		var input=$("#tags");
		var value=input.val().trim();
		var label_regex=/^[a-zA-Z0-9]+$/;
		var btn=$("#label-btn");
		input.parent().parent().find(".error").text(" ");
		if(value.length<=3||value.length>20)
			{
			input.parent().parent().find(".error").text("The label's text has to be between 3 and 20 characters");
			btn.prop("disabled",true);
			return false;
			}
		if(!value.match(label_regex))
			{
			input.parent().parent().find(".error").text("Alpha-numeric characters only");
			btn.prop("disabled",true);
			return false;
			}
		btn.prop("disabled",false);
		return true;
	}
	$("#tags").keyup(validateLabel);
	var availableTags = [ "ActionScript", "AppleScript", "Asp", "BASIC",
				"C", "C++", "Clojure", "COBOL", "ColdFusion", "Erlang",
				"Fortran", "Groovy", "Haskell", "Java", "JavaScript", "Lisp",
				"Perl", "PHP", "Python", "Ruby", "Scala", "Scheme" ];
	$("#tags").autocomplete({
		source : availableTags
	});
    
});
