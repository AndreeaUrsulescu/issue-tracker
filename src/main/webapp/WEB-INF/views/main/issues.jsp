<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="issue/1" >View Issue</a>
<br>
<a href="issues/createIssue" >New Issue</a>
<br>
<a href="logout" >logout</a>

<c:forEach var="issue" items="${issuesList}">
   <p>${issue.getTitle()} ${issue.getUpdateDate()} </p>
</c:forEach>

<c:set var="plus" value="+"/>
<c:set var="minus" value="-"/>

    <button id="previousButton" onclick="issuePagination('${minus}','${listLength}','${itemsPerPage}')">Previous</button>
    <button id="nextButton" onclick="issuePagination('${plus}','${listLength}','${itemsPerPage}')">Next</button>

<div id="issuesContainer">
    <c:out value="${listLength}"/>
</div>
