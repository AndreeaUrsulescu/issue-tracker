$(document).ready(function(){
	
//	function replaceValue(){
//		
//	};
//	
	$("#criteria").parent().find("a").on("click", function(){
		var value = $(this).text();
		$(this).parent().find(".dropdown-toggle").text(value);
		alet(value);
	});
	
//	dropdownLinks
		
	
});