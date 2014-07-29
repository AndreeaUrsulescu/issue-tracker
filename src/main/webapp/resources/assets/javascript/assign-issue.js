$(document).ready(function(){
	$(".viewIssueAssign").on("click", function(){
		$(this).popover('show');
		$(this).popover({
			trigger: 'focus',
			template:'<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
		});
	});
	
    var userList = [];
    $.ajax({
    	dataType: "json",
    	contentType: "application/json;charset=UTF-8",
    	type: "GET",
    	url: window.location.origin + "/issue-tracker/users",
    	success: function(rsp) {
    		for (var index = 0 ; index < rsp.usersList.length ; index++ )
    			userList.push(rsp.usersList[index].userName);
    	}
    });
         
    	
	$("#assigneeInput").autocomplete({
		source: userList
	});
	
});
