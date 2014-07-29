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
    	
    	var labelUrl = "../labels";
    	$.ajax({
    		dataType: "json",
    		type: "GET",
    		url: labelUrl,
    		success: function(rsp) {
    			for(var index = 0; index < rsp.length; index++){
    				availableTags.push(rsp[index].labelName);
    			}
    		}
    	});
    	
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
    
    function addLabel(){
    	//TODO: can still type the name over and over again...
    	if (validateLabel() == true){
	    	//preia url-ul curent
	    	var url = window.location.origin + window.location.pathname + "/addLabel";
	    	var labelData = {
	    			'labelName' : $("#tags").val().trim()
	         	};	
	    	$.ajax({
	    		data: JSON.stringify(labelData),
	    		dataType: "json",
	    		contentType: "application/json;charset=UTF-8",
	    		type: "POST",
	    		url: url,
	    		success: function(rsp) {
	    			if(rsp.response === "success"){
			    			$("#active-labels").append('<span class="issueLabel label label-primary">'+ $("#tags").val().trim()
			    					+'<span class="glyphicon glyphicon-remove"></span>');
		    			if(availableTags.indexOf($("#tags").val().trim()) != -1){
		    				availableTags.splice(availableTags.indexOf($("#tags").val().trim()), 1);
		    			}
	    			} else if(rsp.response === "duplicate"){
	    				$("#tags").parent().parent().find(".error").text("Already exists");
	    			}
	    			$("#tags").val('');
	    		}
	    	});
    	}
    }
    $("#label-editor").hide();
    $(".viewIssueTitleEdit").focus();
    $("#edit").click(editIssue);
    $("#send").click(updateIssue);
    $("#label-add-btn").unbind().click(addLabel);
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
		input.parent().parent().find(".error").text(" ");
		if(value.length<=3||value.length>20)
		{
			input.parent().parent().find(".error").text("The label's text has to be between 3 and 20 characters");
			return false;
		}
		if(!value.match(label_regex))
		{
			input.parent().parent().find(".error").text("Alpha-numeric characters only");
			return false;
		}
		return true;
	}
    $("#tags").keyup(validateLabel);
	var availableTags = [];
	$("#tags").autocomplete({
		source : availableTags
	});
    
});
