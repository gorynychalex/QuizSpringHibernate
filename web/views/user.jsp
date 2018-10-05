<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User </title>
</head>
<body>

<a href="/admin/users/list">User list</a>
<br>

<form:form modelAttribute="userattr" method="post" action="/admin/users/${userattr.username}/edit">
    <%----%>
    Firstname: ${userattr.firstname}
    <br>
    Lastname: ${userattr.lastname}
    <br>
    Middlename: ${userattr.middlename}
    <br>
    Nickname: ${userattr.username}
    <br>
    Password: ${userattr.password}
    <br>
    UserCategory:

    <c:forEach var="auth" items="${userattr.authorities}">
        <c:out value="${auth.authority}"/>
    </c:forEach>
    <br/>
    <input type="submit" value="Edit"/> &nbsp;
    <input type="button" value="Next">
    <br>

</form:form>

</body>
</html>
