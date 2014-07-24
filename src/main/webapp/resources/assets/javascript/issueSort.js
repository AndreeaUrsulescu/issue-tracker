$(document).ready(function(){
	
	$("#sort-navbar").hide();
	$(".criteriaElement").on("click", function(){
		var value = $(this).text();
		value += '<span class="caret"></span>';
		$("#criteria").html(value);
	});
	
	$(".orderElement").on("click", function(){
		var value = $(this).text();
		value += '<span class="caret"></span>';
		$("#order").html(value);
	});
		
	$("#enable-sort").on("click", function(){
		$(this).hide();
		$("#sort-navbar").show();
	});
	
	
});