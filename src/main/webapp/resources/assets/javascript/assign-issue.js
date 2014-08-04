$(document).ready(function(){
	$(".viewIssueAssign").on("click", function(){
		$(this).popover('show');
		$(this).popover({
			trigger: 'focus',
			template:'<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
		});
	});
	
	document.getElementById("assignButton").disabled=true;
	
    var userList = [];
    $.ajax({
    	dataType: "json",
    	contentType: "application/json;charset=UTF-8",
    	type: "GET",
    	url: window.location.origin + window.location.pathname + "/../../../users",
    	success: function(rsp) {
    		for (var index = 0 ; index < rsp.usersList.length ; index++ )
    			userList.push(rsp.usersList[index].userName);
    	}
    });
    
    function validateInput(){
    	
		var input=$("#assigneeInput");
		var value=input.val();
		var findUser = false;
		
		input.parent().find(".error").text(" ");
		for(var index = 0; index < userList.length; index++){
			if(userList[index] == value){
				findUser = true;
			}
		}
		
		if(findUser === false)
		{
			input.parent().find(".error").text("The user doesn`t exist in the database");
			document.getElementById("assignButton").disabled=true;
			return false;
		}
		else {
			document.getElementById("assignButton").disabled=false;
		}
		return true;
	}
    
    $("#assigneeInput").keyup(validateInput);
	$("#assigneeInput").autocomplete({
		source: userList
	});
	$("#assigneeInput").autocomplete({
	    select: function (a, b) {
	        $(this).val(b.item.value);
	        validateInput();
	    }
	});
});
