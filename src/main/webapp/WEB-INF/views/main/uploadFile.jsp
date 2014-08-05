<html>
<head>
<title>AJAX jquery file Upload in Java Web Application</title>
<script src="jquery-1.8.2.js"></script>
<script src="jquery.ajaxfileupload.js"></script>
<script>
$(document).ready(function(){         
 $('input[type="file"]').ajaxfileupload({
     'action': 'UploadFile'
 });

});
</script>

<style type="text/css">
.centered{
width:100%;
margin-left:auto;
margin-right:auto;
text-align:center;
}
</style>
</head>
<body>
<form method="post" >
<div class="centered">

<h2 style="text-align:center;">Using AJAX, jquery and Servlet to upload File in Java Web Application</h2>
<input type="file" name="file" /><br/>
</div>
</form>
</body>
</html>