$(function () {
		
		$("#fileupload").on("click", function(){
			$('#progress .bar').css(
	                'width',
	                '0' + '%'
	            );
		});
		
    $('#fileupload').fileupload({
        dataType: 'json',
 
        done: function (e, data) {
        	
            $("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
 
                $("#uploaded-files").append(
                        $('<tr/>')
                        .append($('<td/>').text(file.filename))
                        .append($('<td/>').text(file.fileType))
                        .append($('<td/>').html("<a href='" + file.issueId + "/download/" + file.id + "'><img src='" + ctx +"/resources/assets/images/Save-icon.png'></a>"))
                        .append($('<td/>').html("<img src='" + ctx + "/resources/assets/images/unX.png' onclick='removeAttachment(" + file.id + ")'>"))
                        );//end $("#uploaded-files").append()
            });
            if (data.result.length == 5) {
            	$('.fileUploadButton').prop("disabled", true);
            }
        },
 
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        }
    });
});

function removeAttachment(id) {
	$.ajax({
		type : "DELETE",
		url : window.location.origin + window.location.pathname+ "/remove/" + id,
		success : function(rsp) {
			console.log(rsp.result);
			if (rsp.result < 5)
				$('.fileUploadButton').prop("disabled", false);
			
			 $("tr:has(td)").remove();
	            $.each(rsp.attachments, function (index, file) {
	 
	                $("#uploaded-files").append(
	                        $('<tr/>')
	                        .append($('<td/>').text(file.filename))
	                        .append($('<td/>').text(file.fileType))
	                        .append($('<td/>').html("<a href='" + file.issueId + "/download/" + file.id + "'><img src='" + ctx +"/resources/assets/images/Save-icon.png'></a>"))
	                        .append($('<td/>').html("<img src='" + ctx + "/resources/assets/images/unX.png' onclick='removeAttachment(" + file.id + ")'>"))
	                        );//end $("#uploaded-files").append()
	            });
		}
	});
}

$(document).ready(function(){
	$(".fileUploadButton").click(function() {
		  $("#fileupload").click();
		});
});