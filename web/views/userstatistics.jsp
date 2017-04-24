<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>

User statistics:
<br>

<c:forEach items="${userstatisticlist}" var="userstat">
    <br>
    Quiz Name: ${userstat.quiz.name}
    &nbsp;&nbsp;
    Mark: ${userstat.mark}
    &nbsp;&nbsp;
    Start time: ${userstat.timestart}
    &nbsp;&nbsp;
    Session: ${userstat.sessionId}
</c:forEach>

</body>
</html>
