<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gorynych
  Date: 08.03.17
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question </title>
</head>
<body>

<p>Quiz:</p>
<p>Question #</p>
<p>${question.text}</p>
<form action="/quiz/" method="post">
<br>
    <c:forEach var="opt" items="${question.options}" varStatus="theCount">
        <input type="radio" name="option" value=${theCount.count-1}><c:out value="${opt.text}"/>
        <br>
    </c:forEach>
    <p>
        <c:if test="${not empty sessionScope.get('prevId')}">
            <button type="submit" formaction="quiz?userId=${param.get('userId')}&quizId=${param.get('quizId')}&questionId=${sessionScope.get("prevId")}" name="action" value="prevQuestionButton">Предыдущий</button>
            &nbsp;&nbsp;
        </c:if>

        <c:if test="${not empty     sessionScope.get('nextId')}">
            <button type="submit" formaction="quiz?userId=${param.get('userId')}&quizId=${param.get('quizId')}&questionId=${sessionScope.get('nextId')}" name="action" value="nextQuestionButton">Следующий</button>
            &nbsp;&nbsp;
        </c:if>

        <button type="submit" name="action" value="finishQuiz">Закончить тест</button></p>
</form>

</body>
</html>
