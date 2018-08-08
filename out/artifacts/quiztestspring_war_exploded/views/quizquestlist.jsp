<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Quiz </title>
    <spring:url value="/resources/theme1/css/bootstrap.min.css" var="maincss"/>
    <link href="<c:url value="${maincss}"/>" rel=stylesheet >

</head>
<body>

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
            <td><a href="/quiz/${quiz.id}/question/${question.id}/option/list">${question.text}</a></td>
            <td>${question.options.size()}</td>
            <td></td>
            <td><a href="/quiz/${quiz.id}/question/${question.id}/edit">Edit</a> </td>
            <td><a href="/quiz/${quiz.id}/question/${question.id}/delete">x</a></td>
            <td></td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="/quiz/${quiz.id}/question/add">
    <button type="submit" formaction="/quiz/list" value="quizlist">Quiz List</button>
    &nbsp;&nbsp;
    <button type="submit" name="question" value="add">ADD</button>
</form>

</body>
</html>