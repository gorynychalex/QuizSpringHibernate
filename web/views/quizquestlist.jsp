<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Quiz </title>
</head>
<body>

<c:set var="rootpath" value="/admin/quiz"/>

<div class="container">
Test: ${quiz.name}

<br>

Question numbers: ${quiz.questions.size()}

<br>

<table border="1">
    <tr>
        <th>N/N</th>
        <th>Question name</th>
        <th>Option's num</th>
        <th>Picture</th>
        <th>Edit</th>
        <th>Remove</th>
        <th>Rename</th>
    </tr>
    <c:forEach var="question" items="${quiz.questions}" varStatus="Count">
        <tr>
            <td>${Count.count}</td>
            <td><a href="${rootpath}/${quiz.id}/question/${question.id}/option/list">${question.text}</a></td>
            <td>${question.options.size()}</td>
            <td>
                <c:if test="${not empty question.picture}">
                    <img src="/resources/images/thumb/quiz/${quiz.id}/questions/${question.id}/${question.picture}">
                </c:if>
            </td>
            <td><a href="${rootpath}/${quiz.id}/question/${question.id}/edit">Edit</a> </td>
            <td><a href="${rootpath}/${quiz.id}/question/${question.id}/delete">x</a></td>
            <td></td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="${rootpath}/${quiz.id}/question/add">
    <button type="submit" name="question" value="add">Add Question</button>&nbsp;&nbsp;
    <button type="submit" formaction="${rootpath}/list" value="quizlist">Quiz List</button>
</form>
</div>

<script src="/resources/theme1/js/main.js"></script>
</body>
</html>