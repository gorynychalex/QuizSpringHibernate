<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<form:form modelAttribute="userattr" method="post" action="/user/add">
    <form:label path="lastname">Lastname:</form:label>
    <form:input path="lastname" />
    <br>
    <form:label path="firstname">Firstname:</form:label>
    <form:input path="firstname" />
    <br>
    <form:label path="middlename">Middlename:</form:label>
    <form:input path="middlename" />
    <br>
    <form:label path="nickname">Nickname:</form:label>
    <form:input path="nickname" />
    <br>
    <form:label path="password">Password:</form:label>
    <form:input path="password" />
    <br>
    <%--<form:label path="usercategory">Category of user:</form:label>--%>
    <%--<form:input path="usercategory" />--%>
    <%--<br>--%>
    <input type="submit" value="Add user"/>
</form:form>
</body>
</html>
