<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

    <spring:url value="/resources/theme1/css/bootstrap.min.css" var="bootstrapcss"/>
    <link href="<c:url value="${bootstrapcss}"/>" rel=stylesheet >


</head>
<body>

<nav class="navbar navbar-default">
    <div class="container">

        <div class="navbar-header">
            <a class="navbar-brand" href="/start">Quiz System</a>
        </div>

        <div class="nav navbar-nav" style="float: right;">
            <c:if test="${not empty pageContext.request.userPrincipal.principal.username}">
                <li class="active"><a href="/statistic/user">User: ${pageContext.request.userPrincipal.principal.username} </a> </li>
                <li><a href="/login.jsp?logout">Logout</a> </li>
            </c:if>
            <c:if test="${empty pageContext.request.userPrincipal.principal.username}">
                <li class="active"><a href="/start/auth"> Login </a> </li>
            </c:if>
        </div>

    </div>
</nav>

    <div class="container">
        Dear &nbsp;
        <c:if test="${not empty pageContext.request.userPrincipal.principal.username}">
             ${pageContext.request.userPrincipal.principal.username},
        </c:if>
        <c:if test="${empty pageContext.request.userPrincipal.principal.username}">
            noname,
        </c:if>

        <p>you result:</p>

        <c:forEach var="mark" items="${marks}" varStatus="count">
            ${count.count}) ${mark}<br>
        </c:forEach>

        <p>Entirely: ${markfull}</p>

        <br>
        <form action="/start/quiz">
            <input type="hidden" name="quizid" value="${quizId}"/>
            <button type="submit" >Restart Test</button>
            &nbsp;&nbsp;
            <button type="submit" name="quizlist" formaction="/start">START</button>
        </form>
    </div>

</body>
</html>