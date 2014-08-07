$(function () {
		
	$("#fileupload").on("click", function(){
		$("#editIssueFileUpload").children('.error').text("");
		$('#progress .bar').css(
	               'width',
	               '0' + '%'
	           );
		
    $('#fileupload').fileupload({
        dataType: 'json',
        
        add: function(e, data) {
            var uploadErrors = [];
            var acceptFileTypes = /^image\/(gif|jpe?g|png)$/i;
            if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
                uploadErrors.push('Not an accepted file type');
            }
            
            if(data.originalFiles[0]['size'] != 0 && data.originalFiles[0]['size'] > 5000000) {
                uploadErrors.push('Filesize is too big');
            }
            if(uploadErrors.length > 0) {
            	for (var i = 0; i < uploadErrors.length; i++) {
            		$("#editIssueFileUpload").children('.error').append(uploadErrors[i]).append("<br>");
            	}
            } else {
                data.submit();
            }
            
    },
 
        done: function (e, data) {
        	
            $("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
 
                $("#uploaded-files").append(
                        $('<tr/>')
                        .append($('<td/>').text(file.filename))
                        .append($('<td/>').text(file.fileType))
                        .append($('<td/>').html("<a href='" + file.issueId + "/download/" + file.id + "'><img src='" + ctx +"/resources/assets/images/Save-icon.png'></a>"))
                        .append($('<td/>').html("<img src='" + ctx + "/resources/assets/images/unX.png' onclick='removeAttachment(" + file.id + ")'>"))
                        );
            });
            if (data.result.length >= 5) {
            	$('.fileUploadButton').prop("disabled", true);
            	$('#fieldsetRemove').prop("disabled", true);
            	$('#fileupload').prop("disabled", true);
            };
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
});

function removeAttachment(id) {
	$("#send").prop('disabled',false);
	
	$.ajax({
		type : "DELETE",
		url : window.location.origin + window.location.pathname+ "/remove/" + id,
		success : function(rsp) {
			console.log(rsp.result);
			if (rsp.result < 5) {
				$('.fileUploadButton').prop("disabled", false);
				$('#fieldsetRemove').prop("disabled", false);
				$('#fileupload').prop("disabled", false);
			}
			 $("tr:has(td)").remove();
	            $.each(rsp.attachments, function (index, file) {
	 
	                $("#uploaded-files").append(
	                        $('<tr/>')
	                        .append($('<td/>').text(file.filename))
	                        .append($('<td/>').text(file.fileType))
	                        .append($('<td/>').html("<a href='" + file.issueId + "/download/" + file.id + "'><img src='" + ctx +"/resources/assets/images/Save-icon.png'></a>"))
	                        .append($('<td/>').html("<img src='" + ctx + "/resources/assets/images/unX.png' onclick='removeAttachment(" + file.id + ")'>"))
	                        );
	            });
		}
	});
}

$(document).ready(function(){
	$(".fileUploadButton").click(function() {
		$("#send").prop('disabled',false);
		  $("#fileupload").click();
		});
});