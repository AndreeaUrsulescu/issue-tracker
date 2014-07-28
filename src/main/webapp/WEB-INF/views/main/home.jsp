<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div id="contentHome" style="background-image: url('resources/assets/images/background.png');">

	<p></p>
	
	<div id="middleHome" >
		<img style="margin-top: 150px; margin-left: 90px; margin-bottom: 15px;" src="resources/assets/images/homeLogo.png">
		
		<p id="motto">We see the point on your issues</p>
		
		<a href="${pageContext.request.contextPath}/login" class="btn homeBtn" >Login</a>
		<a href="${pageContext.request.contextPath}/register" class="btn homeBtn">Register</a>
	</div>

</div>

<footer class="footer nav navbar-default ">
	<div class="container">
		<small>
			<a href="#" target="blank">&copy; Issue Tracker </a>
		</small>
	
		<nav>
			<ul>
				<li><a href="https://github.com/">Git Hub</a></li>
			</ul>
		</nav>
	</div>
</footer>