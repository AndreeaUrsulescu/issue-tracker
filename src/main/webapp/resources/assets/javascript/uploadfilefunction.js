$(function () {
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
                        .append($('<td/>').html("<a href='" + file.issueId + "/remove/" + file.id + "'><img src='" + ctx + "/resources/assets/images/unX.png' onclick='removeAttachment(" + file.id + ")'></a>"))
                        );//end $("#uploaded-files").append()
            }); 
        },
 
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        },
 
        dropZone: $('#dropzone')
    });
});

function removeAttachment(id) {
	alert("i'm in");
	$.ajax({
		type : "DELETE",
		url : "remove/" + id,
		success : function(rsp) {
			console.log(rsp.result);
		}
	});
}