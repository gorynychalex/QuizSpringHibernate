<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz List</title>
</head>
<body>

<p>Quiz list table:</p>

<table border="1">
    <tr>
        <th>N/N</th>
        <th>Quiz name</th>
        <th>Question nums</th>
        <th>Picture</th>
        <th>Edit</th>
        <th>Remove</th>
        <th>Rename</th>
    </tr>
    <c:forEach var="quiz" items="${quizlist}" varStatus="Count">
        <tr>
            <td>${Count.count}</td>
            <td><a href="/quiz/questions/${quiz.id}">${quiz.name} </a></td>
            <td>${quiz.qnums}</td>
            <td></td>
            <td></td>
            <td><a href="/quiz/delete/${quiz.id}">x</a></td>
            <td></td>
        </tr>
    </c:forEach>
</table>

<form action="/quiz/add">
<button type="submit" name="quizadd" value="new">ADD</button>
</form>

</body>
</html>
