$(document).ready(function(){
	$("#selectT").hide();
	$("#selectS").change(function(){
		if($(this).val()==="state"){
			$("#searchField").hide("slide");
			$("#selectT").show("slide");
		}
		else
		{
			$("#selectT").hide("slide");
			$("#searchField").show("slide");
		}
	});

});