window.onload=function(){

};

function validateForm() {
	
		if(document.getElementById("inputUserName").value===""||document.getElementById("inputPassword").value==="")
		{
			document.getElementById("inputUserName").placeholder="You must enter an username!";
			return false;
		}
	
		if(document.getElementById("inputUserName").value.length<3)
		{
			document.getElementById("inputUserName").placeholder="Username must have at least 3 characters!";
			return false;
		}
	

		 if(document.getElementById("inputPassword").value.length<5)
		{
			alert("The password must have at least 5 characters");
			document.getElementById("inputPassword").value="";
			return false;
		}
		else if(document.getElementById("inputUserName").value.length>15)
		{
			alert("Username must not be longer than 15 characters");
			return false;
		}
		return true;
}