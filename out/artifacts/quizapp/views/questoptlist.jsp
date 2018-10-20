<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="rootpath" value="/admin/quiz"/>

<html>
<head>
    <title>SHOW QUESTION</title>
    <%--<spring:url value="/resources/theme1/css/bootstrap.min.css" var="maincss"/>--%>
    <%--<link href="<c:url value="${maincss}"/>" rel=stylesheet >--%>
</head>
<body>

<div class="container">
<p>Quiz:<a href="${rootpath}/${quiz.id}/question/list">&nbsp;${quiz.name} </a> </p>
<p>${question.text}</p>

<table border="1">
    <tr>
        <th>N/N</th>
        <th>Option text</th>
        <th>Correct</th>
        <th>Picture</th>
        <th>Edit</th>
        <th>Remove</th>
        <th>Rename</th>
    </tr>
    <c:forEach var="option" items="${question.options}" varStatus="Count">
        <tr>
            <td>${Count.count}</td>
            <td>${option.text}</td>
            <td>${option.correct}</td>
            <td><img src="/resources/images/thumb/quiz/${quiz.id}/questions/${question.id}/options/${option.id}/${option.picture}"/></td>
            <td><a href="${rootpath}/${quiz.id}/question/${question.id}/option/${option.id}/edit">Edit</a></td>
            <td><a href="${rootpath}/${quiz.id}/question/${question.id}/option/${option.id}/delete">x</a></td>
            <td></td>
        </tr>
    </c:forEach>
</table>

<br>
<form action="${rootpath}/${quiz.id}/question/${question.id}/option/add">

    <button type="submit" name="option" value="new">Add Option</button>
    &nbsp;&nbsp;
    <button type="submit" formaction="${rootpath}/list" value="quizlist">Quiz List</button>
    &nbsp;&nbsp;
    <button type="submit" formaction="${rootpath}/${quiz.id}/question/list">Question list of ${quiz.name}</button>
    &nbsp;&nbsp;
</form>

    <br>
    <a href="${rootpath}/${quiz.id}/question/${question.id}/edit">Edit</a>
    &nbsp;&nbsp;
    <a href="${rootpath}/${quiz.id}/question/${question.id}/delete">Delete</a>
    &nbsp;&nbsp;

</div>

<script src="/resources/theme1/js/main.js"></script>
</body>
</html>
