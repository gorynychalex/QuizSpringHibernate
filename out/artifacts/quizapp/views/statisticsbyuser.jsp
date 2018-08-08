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
    <spring:url value="/resources/theme1/css/bootstrap.min.css" var="maincss"/>
    <link href="<c:url value="${maincss}"/>" rel=stylesheet >
    <link href="/resources/theme1/css/main.css">
    <script src="<c:url value="/resources/theme1/js/jquery.js"/> "></script>

</head>

<body>


<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <h4><a href="/start">Quiz System</a></h4>
        </div>
        <div class="nav navbar-nav" style="float: right;">
            <li class="active"><a href="#">User: ${user.nickname}</a> </li>
            <li><a href="#">Выйти</a> </li>
        </div>

    </div>

</nav>

<div class="container">

<div id="msg">
&nbsp;&nbsp; ${userstatisticlist.get(0).user.lastname}
&nbsp;&nbsp; ${userstatisticlist.get(0).user.firstname}
</div>

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

</div>

<script src="<c:url value="/resources/theme1/js/main.js"/> "></script>

</body>

</html>
