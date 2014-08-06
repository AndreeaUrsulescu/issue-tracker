$(document).ready(function(){
	//clear content on page "re-load" (after closing and reopening tab)
	$("#comment-area").val('');
	
  	if($("#current-state").hasClass("closed")){
  		$("#comments").prop('hidden',"display: none;");
  	}
  	
	function clearContent(){
		tinyMCE.get('comment-area').setContent('');
		$("#error-msg").text(" ");
	};
	
	function validateComment(){
		var comment = tinyMCE.get('comment-area').getContent();
		$("#error-msg").text(" ");		
		if(comment.length == 0){
			$("#error-msg").text("Comment cannot be empty");
			return false;
		}
		
		return true;
	}
	
	function sendContent(){
		var valid = validateComment();
		
		var content = tinyMCE.get('comment-area').getContent();
		if (valid){
			var comment = {
					'content' : content
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