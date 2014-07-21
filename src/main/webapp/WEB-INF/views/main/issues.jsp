<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<a href="issues/issue/1" >View Issue</a>

<c:forEach var="issue" items="${issuesList}">
   <p>${issue.getTitle()} ${issue.getUpdateDate()} </p>
</c:forEach>