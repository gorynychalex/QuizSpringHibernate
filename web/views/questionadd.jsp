<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: gorynych
  Date: 15.03.17
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add the question</title>
</head>
<body>
<form:form modelAttribute="questionattr" method="post" action="/question/add">
    <form:label path="name">Question Text:</form:label>
    <form:input path="name" />
    <br>
    <form:label path="picture">picture url:</form:label>
    <form:input path="picture" />
    <br>
    <input type="submit" value="Add"/>
</form:form>
</body>
</html>
