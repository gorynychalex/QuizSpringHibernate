<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add quiz</title>
</head>
<body>

<p>Create new QUIZ:</p>

<form:form modelAttribute="quizattr" method="post" action="/quiz/add">
    <form:label path="name">Quiz name:</form:label>
    <form:input path="name" />
    <br>
    <form:label path="picture">picture url:</form:label>
    <form:input path="picture" />
    <br>
    <input type="submit" value="Add"/>
</form:form>

</body>
</html>
