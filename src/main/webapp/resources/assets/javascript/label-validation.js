//label-btn
//label-input

$(document).ready(function() {
	function validateLabel(){
		var input="#label-input";
		var value=input.val();
		var label_regex=/^[a-zA-Z0-9]+$/;
		var btn="#label-btn";
		if(value.length<=3||value.length>20)
			{
			input.parent().find("span").text("The label's text has to be between 3 and 20 characters");
			btn.prop("disabled",true);
			return false;
			}
		if(!value.match(label_regex))
			{
			input.parent().find("span").text("Alpha-numeric characters only");
			btn.prop("disabled",true);
			return false;
			}
		btn.prop("disabled",false);
		return true;
		
	}
	$("#label-input").keyup(validateLabel);
});