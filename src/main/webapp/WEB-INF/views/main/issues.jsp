<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="${pageContext.request.contextPath}/issues/createIssue" >New Issue</a>
<br>


<c:set var="plus" value="+"/>
<c:set var="minus" value="-"/>
    <p id="numberOfIssues" style="display:none"><c:out value="${listLength}"/></p>
    <p id="issuesPerPage" style="display:none"><c:out value="${itemsPerPage}"/></p>
    
    <button id="previousButton" onclick="issuePagination('${minus}','${listLength}','${itemsPerPage}')">Previous</button>
    <button id="nextButton" onclick="issuePagination('${plus}','${listLength}','${itemsPerPage}')">Next</button>


<div id="issuesContainer">

	<c:forEach var="issue" items="${issuesList}">
		<button onclick="viewIssue('${issue.getId()}')">	<c:out value="${issue.getTitle()}"/> ${issue.getUpdateDate()}</button>
	</c:forEach>

</div>
