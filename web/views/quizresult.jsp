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
    <div class="col-md-2 col-sm-12"></div>
    <div class="col-md-8 col-sm-12">
    <div class="panel panel-default">

        <div class="panel-heading">
            Dear
            <c:if test="${not empty pageContext.request.userPrincipal.principal.username}">
                ${pageContext.request.userPrincipal.principal.username},
            </c:if>
            <c:if test="${empty pageContext.request.userPrincipal.principal.username}">
                Anonymous,
            </c:if>
            your result:
        </div>

        <div class="panel-body">

            <ul class="list-group">
                <li class="list-group-item">
                    Attempted questions:
                    <span class="badge">${quizresult.questionsAttempted}</span>
                </li>

                <li class="list-group-item">
        Un-Attempted questions: <span class="badge">${quizresult.questionsUnAttempted}</span>
                </li>


                <li class="list-group-item">
                    Total correct answers: <span class="badge">${quizresult.ansTrue}</span>
                </li>

                <li class="list-group-item">
                    Total wrong answers: <span class="badge">${quizresult.ansFalse}</span>
                </li>

                <li class="list-group-item">
            Score: <span class="badge">${quizresult.score}</span>
                </li>

                <li class="list-group-item">
                    Mark result: <span class="badge">${quizresult.mark}</span>
                </li>
            </ul>


        <details>
        <ul class="list-group">
        <li class="list-group-item disabled"><summary>Marks by questions:</summary> </li>
        <c:forEach items="${quizresult.questionsresult}" var="markentry" varStatus="count">
            <li class="list-group-item">
            ${count.count})&nbsp ${markentry.question.text}   <span class="badge">${markentry.mark} </span>
            </li>
        </c:forEach>
        </ul>
        </details>

        <nav aria-label="navquiz">
            <ul class="pager">
            <form action="/start/quiz">
            <input type="hidden" name="quizid" value="${quizresult.quiz.id}"/>
            <input type="hidden" name="action" value="startQuiz"/>
            <li><button class="btn btn-default" type="submit" >Restart Test</button></li>
            <li><button class="btn btn-info" type="submit" name="quizlist" formaction="/start">START</button></li>

                <!-- Show SaveResult button if Anonymous-->
                <c:if test="${empty pageContext.request.userPrincipal.principal.username}">
                    <li><button class="btn btn-default" type="submit" formaction="/start/auth">Save Result</button></li>
                </c:if>
        </form>
            </ul>
        </nav>
    </div>
    </div>
</body>
</html>