$(document).ready(function(){
	
	//clear content on page "re-load" (after closing and reopening tab)
	$("#comment-area").val('');
	
	function clearContent(){
		$("#comment-area").val('');
		$("#counter").text(500);
	};
	
	function validateComment(){
		var comment = $("#comment-area").val().trim();
		var MAX=500;
		$("#counter").text(MAX-comment.length);
		if(comment.length == 0)
			return false;
		return true;
	}
	
	function sendContent(){
		//TODO: check if comment is valid
		//(see the function above) and send the trimmed content
	}
	$("#clear-btn").click(clearContent);
	$("#send-btn").click(clearContent);
	$("#comment-area").keyup(validateComment);
	
});