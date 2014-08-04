$(document).ready(function(){

	$("#message-area").val('');
	
//  	if($("#current-state").hasClass("closed")){
//  		$("#messages").prop('hidden',"display: none;");
//
//  	}
  	
	function clearContent(){
		$("#message-area").val('');
		$("#error-msg").text(" ");
		$("#counter").text(700);
	};
	
	function validateComment(){
		var message = $("#message-area").val().trim();
		var MAX=700;
		$("#error-msg").text(" ");
		$("#counter").text(MAX-message.length);
		if(message.length == 0){
			$("#error-msg").text("Comment must not be empty");
			return false;
		}
		if(message.length > 700){
			$("#error-msg").text("Comment must be less than 500");
			return false;
		}
		return true;
	}
	
	function sendContent(){
		var valid = validateComment();
		if (valid){
			var message = {
					'content' : $("#message-area").val().trim(),
					'range' : 0,
					'link' : 'aaa',
					'owner': 'wlwlw'
				};
				
				//preia url-ul curent
				var url = window.location.origin + window.location.pathname;
					
				$.ajax({
					data: JSON.stringify(message),
					dataType: "json",
					contentType: "application/json;charset=UTF-8",
					type: "POST",
					url: url,
					success: function(rsp) {
						if (rsp.code === "success")
						{
							alert("tada");
							location.reload();
						}
					}
				});
		}
	}
	$("#clear-btn").click(clearContent);
	$("#sendMsg").click(sendContent);
	$("#message-area").keyup(validateComment);
	
});