$(document).ready(function(){
	
	//clear content on page "re-load" (after closing and reopening tab)
	$("#comment-area").val('');
	
	function clearContent(){
		$("#comment-area").val('');
		$("#error-msg").text(" ");
		$("#counter").text(500);
	};
	
	function validateComment(){
		var comment = $("#comment-area").val().trim();
		var MAX=500;
		$("#error-msg").text(" ");
		$("#counter").text(MAX-comment.length);
		if(comment.length == 0){
			$("#error-msg").text("Comment must not be empty");
			return false;
		}
		if(comment.length > 500){
			$("#error-msg").text("Comment must be less than 500");
			return false;
		}
		return true;
	}
	
	function sendContent(){
		var valid = validateComment();
		if (valid){
			var comment = {
					'content' : $("#comment-area").val().trim()
				};
				
				//preia url-ul curent
				var url = window.location.origin + window.location.pathname + "/comment";
					
				$.ajax({
					data: JSON.stringify(comment),
					dataType: "json",
					contentType: "application/json;charset=UTF-8",
					type: "POST",
					url: url,
					success: function(rsp) {
						if (rsp.code == "success")
						{
							location.reload();
						}
						else
						{
							alert(rsp.comments[0].content);
						}
					}
				});
		}
	}
	$("#clear-btn").click(clearContent);
	$("#send-btn").click(sendContent);
	$("#comment-area").keyup(validateComment);
	
});