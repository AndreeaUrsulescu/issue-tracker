$(function () {
		
	$("#fileupload").on("click", function(){
		$('#progress .bar').css(
	               'width',
	               '0' + '%'
	           );
		
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
                        );
            });
            if (data.result.length >= 5) {
            	$('#fileupload').prop("disabled", true);
            };
        },
        
        send: function(e, data) {
        	var uploadedFiles = $('#tableSize').children();
        	var tableSize = uploadedFiles.length;
        	
        	alert("tata");
        	//alert(5 - tableSize + data.files.length + 1);
        	if ( (5 - tableSize + data.files.length + 1) > 0)
        	{
        		alert(5 - tableSize + data.files.length);
        		return false;
        	};
     
        },
        
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 5,
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
	$.ajax({
		type : "DELETE",
		url : window.location.origin + window.location.pathname+ "/remove/" + id,
		success : function(rsp) {
			console.log(rsp.result);
			if (rsp.result < 5)
				$('#fileupload').prop("disabled", false);
			
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