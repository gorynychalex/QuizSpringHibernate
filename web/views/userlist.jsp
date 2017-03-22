<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>

<p>Students:</p>
<c:forEach var="user" items="${userlist}" varStatus="theCount">
    <c:out value="${theCount.count}"/> )&nbsp; <c:out value="${user.firstname}"/> &nbsp; ${user.lastname}
    <br>
</c:forEach>

</body>
</html>
