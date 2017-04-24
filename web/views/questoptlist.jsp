<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SHOW QUESTION</title>
</head>
<body>

<p><a href="/quiz/questions/${quiz.id}"> Quiz:${quiz.name} </a> </p>
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
            <td></td>
            <td><a href="/quiz/${quiz.id}/question/${question.id}/option/${option.id}/edit">Edit</a></td>
            <td><a href="/quiz/${quiz.id}/question/${question.id}/option/${option.id}/delete">x</a></td>
            <td></td>
        </tr>
    </c:forEach>
</table>

<br>
<form action="/quiz/${quiz.id}/question/${question.id}/option/add">
    <button type="submit" formaction="/quizlist" value="quizlist">Quiz List</button>
    &nbsp;&nbsp;
    <button type="submit" formaction="/quiz/questions/${question.id}">Quiz List of ${quiz.name}</button>
    &nbsp;&nbsp;
    <button type="submit" name="option" value="new">ADD</button>
</form>

</body>
</html>
