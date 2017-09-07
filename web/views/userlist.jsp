<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>

<p>Students:</p>

<table border="1">
    <tr>
        <th>N/N</th>
        <th>Name</th>
        <th>Group</th>
        <th>Picture</th>
        <th>Edit</th>
        <th>Remove</th>
    </tr>
    <c:forEach var="user" items="${userlist}" varStatus="theCount">
    <tr>
        <td>${theCount.count}</td>
        <td><a href="/user/${user.id}"><c:out value="${user.firstname}"/> &nbsp; ${user.lastname}</a> </td>
        <td>Group</td>
        <td>Picture</td>
        <td><a href="/user/${user.id}/edit">Edit</a></td>
        <td><a href="/user/${user.id}/delete">x</a></td>
    </tr>
    </c:forEach>
</table>


<form action="/user/add">
    <button type="submit" name="quizadd" value="new">ADD</button>
</form>


</body>
</html>
