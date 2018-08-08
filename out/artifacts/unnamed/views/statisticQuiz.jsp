<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <spring:url value="/resources/theme1/css/bootstrap.min.css" var="maincss"/>
    <link href="<c:url value="${maincss}"/>" rel=stylesheet >

</head>
<body>

<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <h3><a href="/start">Quiz System</a></h3>
        </div>
        <ol class="nav navbar-nav">
            <li class="active"><a href="#">User: </a> </li>
            <li><a href="#">Выйти</a> </li>
        </ol>

    </div>
    <div class="container" style="background-color: darkcyan; color: white">
        <h2>Statistic quiz:${userquizservice.quiz.name}</h2>
        <p>sessionId: ${sessionId}</p>
        <h2>mark: ${userquizservice.mark}</h2>
    </div>
</nav>

<br>
<div class="container" style="background-color: white; color: darkcyan;">
<h3>Questions:</h3>
<br>

<c:forEach items="${squestions}" var="squestion" varStatus="count">
    <br>
    ${count.count}) Statistic question Id: ${squestion.id}
    <%--Question Id: ${squestion.question.id}--%>
    <p>Question text: ${squestion.question.text}</p>

    <br>
    <%--<c:forEach var="soption" items="${squestion.statisticOptionsList}">--%>
        <%--&lt;%&ndash;&nbsp;&nbsp; Option: ${soption.option.id} &nbsp;&nbsp; ${soption.option.text}&ndash;%&gt;--%>
        <%--<br>--%>
        <%--&lt;%&ndash;Option isCorrect: ${soption.option.correct}&ndash;%&gt;--%>
        <%--<c:if test="${soption.option.correct}">            [x]        </c:if>--%>
        <%--<c:if test="${!soption.option.correct}">            [ ]        </c:if>--%>
        <%--&lt;%&ndash;&nbsp;&nbsp; ${soption.option.text}&ndash;%&gt;--%>
    <%--</c:forEach>--%>
    <br>
    <c:forEach var="option" items="${squestion.question.options}">
        <%--&nbsp;&nbsp;&nbsp;&nbsp; Original Option: ${option.id} &nbsp;&nbsp; ${option.correct}--%>
        <p>
        <c:if test="${option.correct}">            [x]        </c:if>
        <c:if test="${!option.correct}">            [ ]        </c:if>

            <c:forEach var="soption" items="${squestion.statisticOptionsList}">
                <%--&nbsp;&nbsp; Option: ${soption.option.id} &nbsp;&nbsp; ${soption.option.text}--%>
                <%--Option isCorrect: ${soption.option.correct}--%>
                <c:choose>
                    <c:when test="${soption.option.id eq option.id}">            [v]        </c:when>
                    <c:otherwise>[ ]</c:otherwise>
                </c:choose>
                <%--<c:if test="${soption.option.id != option.id}">            [ ]        </c:if>--%>
                <%--<c:otherwise>   [ ] </c:otherwise>--%>
                <%--&nbsp;&nbsp; ${soption.option.text}--%>
            </c:forEach>



        ${option.text}
        </p>
    </c:forEach>
    <p>RESULT: ${resultByQuiz.get(count.count-1)}</p>
</c:forEach>

</div>

</body>
</html>
