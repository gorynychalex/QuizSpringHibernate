<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quiz </title>
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
            <td><a href="/questoptlist?quizid=${quiz.id}&questionid=${question.id}">${question.text}</a></td>
            <td>${question.options.size()}</td>
            <td></td>
            <td><a href="/quiz/${quiz.id}/question/${question.id}/edit">Edit</a> </td>
            <td><a href="/quiz/${quiz.id}/question/delete/${question.id}">x</a></td>
            <td></td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="/quiz/${quiz.id}/question/add">
    <button type="submit" formaction="/quizlist" value="quizlist">Quiz List</button>
    &nbsp;&nbsp;
    <button type="submit" name="question" value="add">ADD</button>
</form>


<%--<c:forEach var="question" items="${quiz.questions}" varStatus="theCount1">--%>
    <%--<c:out value="${theCount1.count}"/>)<c:out value="${question.getText()}"/>--%>
    <%--<br>--%>
    <%--<c:forEach var="opt" items="${question.options}" varStatus="theCount">--%>
        <%---&nbsp;  <c:out value="${opt.text}"/>--%>
        <%--<br>--%>
    <%--</c:forEach>--%>
<%--</c:forEach>--%>


</body>
</html>
