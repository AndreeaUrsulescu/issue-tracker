$(document).ready(function(){


	
    function updateIssue() {
    	var valid = ValidateTitle();
    	
    	if(valid){
    	$("#send").prop('disabled',false); $("#send").addClass("validB");
    	
    	// se completeaza cu datele din formular (content, title, state)
    	var issue = {
    		'content' : tinyMCE.get('issueContent').getContent(),
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
    }
    function editIssue() {
    	
    	var labelUrl = window.location.origin + window.location.pathname + "/../../labels";
    	$.ajax({
    		dataType: "json",
    		type: "GET",
    		url: labelUrl,
    		success: function(rsp) {
    			for (var key in rsp) {
    				availableTags.push(rsp[key]);
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
//    			$("#issueContent").val(rsp.issue.content);
    			tinyMCE.get('issueContent').setContent(rsp.issue.content);
				var id ='#'+rsp.issue.state; 
				
				$(id).addClass('current');
//    			$("#issue-states:nth-child("+rsp.issue.state+")").addClass('current');
    		}
    	});
    	$("#active-labels").html($("#view-labels").html());
    	$(".editIssueContent").show();
    	$(".editIssueView").hide();
    	$("#newComment").hide();
    	$("#label-editor").show();
    	
    }
    
    function addLabel(){
    	$("#send").prop('disabled',false); 
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
			    		
	    				//show label in UI
	    				$(".selected-labels").append('<span class="issueLabel label label-primary">'+ $("#tags").val().trim()
			    					+'<span class="label-remove glyphicon glyphicon-remove"></span>');
	    				//add label to autocomplete
	    				availableTags.push($("#tags").val().trim());
	    				
	    			} else if(rsp.response === "duplicate"){
	    				//show error message if label already exists
	    				$("#tags").parent().parent().find(".error").text("Already exists");
	    			}
	    			//clear input box
	    			$("#tags").val('');
	    		}
	    	});
    	}
    }
    
    function removeLabel(){
    	$("#send").prop('disabled',false); 
    	var url = window.location.origin + window.location.pathname + "/removeLabel";
    	var labelData = {
    			'labelName' : $(this).parent().text().trim()
         	};
    	var element = $(this).parent();
    	$.ajax({
    		data: JSON.stringify(labelData),
    		dataType: "json",
    		contentType: "application/json;charset=UTF-8",
    		type: "DELETE",
    		url: url,
    		success: function(rsp) {
    			if(rsp.response === "success"){
    				element.remove();
    			};
    		}
    	});
    }
    
    $("#label-editor").hide();
    $(".editIssueContent").hide();
    
    $(".viewIssueTitleEdit").focus();
    
    $("#edit").click(editIssue);
    $("#send").unbind().click(savaB);
    $("#label-add-btn").click(addLabel);
    $(".selected-labels").on("click", ".label-remove", removeLabel);
	
    $("#reset").click(function(){
        location.reload();
        //reload page in order to get the most recent "issue"
    });

    //for each subdiv
    $('#issue-states > .viewIssueState').on('click',function(){   	
    		$('div').removeClass('current');
    	       $(this).addClass('current');
    	

     //set current active state
       $("#send").prop('disabled',false); $("#send").addClass("validB");
       
       //issue title is valid?
      	if (($("#issueTitle").val().trim().length < 5)) {
      		$("#issueTitle").parent().find("span").text("Your title cannot be empty");
      		$("#send").prop('disabled',true); $("#send").removeClass("validB");
   		return false;
      	}
    });
    /** Label validation and insert **/
    function validateLabel(){
		var input=$("#tags");
		var value=input.val().trim();
		var label_regex=/^[a-zA-Z0-9]+$/;
		input.parent().parent().find(".error").text(" ");
		if(value.length < 3|| value.length > 20)
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
	$("#tags").autocomplete({
	    select: function (a, b) {
	        $(this).val(b.item.value);
	        validateLabel();
	    }
	});
	
	function savaB(){
		if($( "#send" ).hasClass( "validB" ))
			updateIssue();
		else
			location.reload();
	}
});
