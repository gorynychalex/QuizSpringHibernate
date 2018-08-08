<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: gorynych
  Date: 24.04.17
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Statistics</title>
    <spring:url value="/resources/theme1/css/main.css" var="maincss"/>
    <link href="<c:url value="${maincss}"/>" rel=stylesheet >
    <script src="<c:url value="/resources/theme1/js/jquery.js"/> "></script>
    <script src="<c:url value="/resources/theme1/js/main.js"/> "></script>
</head>
<body>

<div id="msg">
&nbsp;&nbsp; ${userstatisticlist.get(0).user.lastname}
&nbsp;&nbsp; ${userstatisticlist.get(0).user.firstname}
</div>

<br>
<table border="1">
    <tr>
        <th>Session Id</th>
        <th>Quiz Name</th>
        <th>Questions nums</th>
        <th>Mark</th>
        <th>Start time</th>
        <th>Stop time</th>
    </tr>

    <c:forEach items="${userstatisticlist}" var="userstat">
    <tr>
        <td>${userstat.sessionId}</td>
        <td>${userstat.quiz.name}</td>
        <td>${userstat.quiz.qnums}</td>
        <td>${userstat.mark}</td>
        <td>${userstat.timestart}</td>
        <td></td>
    </tr>
    </c:forEach>

</table>

</body>

</html>
