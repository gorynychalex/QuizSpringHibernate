<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8"/>
    <title>Add new user</title>
</head>
<body>
<c:if test="${not empty userattr.firstname}">
    <c:url var="urlaction" value="/user/edit"/>
    <c:set var="buttonText" value="Edit"/>
</c:if>
<c:if test="${empty userattr.firstname}">
    <c:url var="urlaction" value="/user/add"/>
    <c:set var="buttonText" value="Add"/>
</c:if>
<form:form modelAttribute="userattr" method="post" action="${urlaction}">
    <form:hidden path="id"/>
    <%--<form:label path="id">id:</form:label>--%>
    <%--<form:input path="id" />--%>

    <form:label path="firstname">Firstname:</form:label>
    <form:input path="firstname" />
    <br>
    <form:label path="lastname">Lastname:</form:label>
    <form:input path="lastname" />
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
    <form:select path="usercategory" >
        <form:options/>
    </form:select>
    <br>
    <input type="submit" value="${buttonText}"/>
</form:form>
</body>
</html>
