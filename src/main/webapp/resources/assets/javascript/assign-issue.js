$(document).ready(function(){
	$(".viewIssueAssign").on("click", function(){
		$(this).popover('show');
		$(this).popover({
			trigger: 'focus',
			template:'<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
		});
	});
	
	var test = ["puppy", "another puppy", "puppy three"];
	
	$("#assigneeInput").autocomplete({
		source: test
	});
});