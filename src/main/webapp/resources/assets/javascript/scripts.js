window.onload=function(){

};

function validateForm() {
		$("#inputUserName").css({"border": "1px solid rgb(200,200,200)"});
		$("#inputPassword").css({"border": "1px solid rgb(200,200,200)"});
		
		if(document.getElementById("inputUserName").value==="")
		{
			document.getElementById("inputUserName").style.border="1px solid rgb(169,68,66)";
			document.getElementById("inputUserName").placeholder="You must enter an username!";
			return false;
		}
	
		else if(document.getElementById("inputPassword").value===""){
			document.getElementById("inputPassword").style.border="1px solid rgb(169,68,66)";
			document.getElementById("inputPassword").placeholder="You must enter a password!";
			return false;
		}
		
		else if(document.getElementById("inputUserName").value.length<3)
		{
			document.getElementById("inputUserName").value="";
			document.getElementById("inputUserName").style.border="1px solid rgb(169,68,66)";
			document.getElementById("inputUserName").placeholder="The username must have at least 3 characters!";
			return false;
		}
		
		else if(document.getElementById("inputUserName").value.length>15)
		{
			document.getElementById("inputUserName").value="";
			document.getElementById("inputUserName").style.border="1px solid rgb(169,68,66)";
			document.getElementById("inputUserName").placeholder="The username must not have more than 15 characters!";
			return false;
		}
	
		else if(document.getElementById("inputPassword").value.length<5)
		{
			document.getElementById("inputPassword").value="";
			document.getElementById("inputPassword").placeholder="The password must have at least 5 characters";
			return false;
		}
		

		return true;
}