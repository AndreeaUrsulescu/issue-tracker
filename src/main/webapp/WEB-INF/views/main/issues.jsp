<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="issue/1" >View Issue</a>
<br>
<a href="issues/createIssue" >New Issue</a>
<br>
<a href="logout" >logout</a>


<c:set var="plus" value="+"/>
<c:set var="minus" value="-"/>
    <p id="numberOfIssues" display="none"><c:out value="${listLength}"/></p>
    <p id="issuesPerPage" display="none"><c:out value="${itemsPerPage}"/></p>
    
    <button id="previousButton" onclick="issuePagination('${minus}','${listLength}','${itemsPerPage}')">Previous</button>
    <button id="nextButton" onclick="issuePagination('${plus}','${listLength}','${itemsPerPage}')">Next</button>


<div id="issuesContainer">

	<c:forEach var="issue" items="${issuesList}">
		<button onclick="viewIssue('${issue.getId()}')">${issue.getTitle()} ${issue.getUpdateDate()}</button>
	</c:forEach>

</div>
