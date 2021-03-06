<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<p>User: ${user.nickname}</p>
<p>Quiz:${quiz.name}</p>
<p>Question #${qnum+1} from ${qnums}</p>
<p>${question.text}</p>
<form action="/quiz" method="post">
    <input type="hidden" name="quizid" value="${quiz.id}"/>
    <input type="hidden" name="userid" value="${user.id}"/>
    <input type="hidden" name="qnum" value="${qnum}"/>
    <input type="hidden" name="sessionid" value="${sessionid}"/>
<br>
    <c:forEach var="opt" items="${question.options}">
        <input type="radio" name="option" value=${opt.id}><c:out value="${opt.text}"/>
        <br>
    </c:forEach>
    <p>
        <c:if test="${qnum > 0}">
            <button type="submit" formaction="/quiz?userid=${quiz.id}&quizid=${quiz.id}&questionid=${qnum-1}" name="button" value="prevQuestion">
                <%--<button type="submit" name="questionid" value="${qnum-1}">--%>
                        PREV</button>
            &nbsp;&nbsp;
        </c:if>

        <c:if test="${qnum < qnums-1}">
            <button type="submit" formaction="/quiz?userid=${quiz.id}&quizid=${quiz.id}&questionid=${qnum+1}" name="button" value="nextQuestion">
                <%--<button type="submit" name="questionid" value="${qnum+1}">--%>
                NEXT</button>
            &nbsp;&nbsp;
        </c:if>

        <button type="submit" name="button" value="finish">FINISH</button></p>

</form>

</body>
</html>
