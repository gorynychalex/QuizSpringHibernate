<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: gorynych
  Date: 21.03.17
  Time: 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result of quiz</title>
</head>
<body>

<p>RESULTS:</p>

<c:forEach var="mark" items="${marks}" varStatus="count">
    ${count.count}) ${mark}<br>
</c:forEach>

<p>Entirely: ${markfull}</p>

<br>
<form action="/quiz">
    <input type="hidden" name="quizid" value="${quizId}"/>
    <input type="hidden" name="userid" value="${userId}"/>
    <button type="submit" >AGAIN THIS TEST</button>
    &nbsp;&nbsp;
    <button type="submit" name="quizlist" formaction="/start">START</button>
</form>

</body>
</html>