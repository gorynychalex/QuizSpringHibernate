<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gorynych
  Date: 29.04.17
  Time: 6:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

Statistic Quiz: ${userquizservice.quiz.name}
<br>

sessionId: ${sessionId}
<br>

mark: ${userquizservice.mark}

<br>

questions:
<br>

<c:forEach items="${squestions}" var="squestion" varStatus="count">
    <br>
    ${count.count}) Statistic question Id: ${squestion.id}
    <br>
    Question Id: ${squestion.question.id}
    <br>
    Question text: ${squestion.question.text}
    <br>
    RESULT: ${resultByQuiz.get(count.count-1)}
    <br>
    <c:forEach var="soption" items="${squestion.statisticOptionsList}">
        &nbsp;&nbsp; Option: ${soption.option.id} &nbsp;&nbsp; ${soption.option.text}
        <br>
        Option isCorrect: ${soption.option.correct}
        <br>
    </c:forEach>
    <br>
    <c:forEach var="option" items="${squestion.question.options}">
        &nbsp;&nbsp;&nbsp;&nbsp; Original Option: ${option.id} &nbsp;&nbsp; ${option.correct}
        <br>
        ${option.text}
        <br>
    </c:forEach>

</c:forEach>



</body>
</html>
