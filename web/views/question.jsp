<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">--%>
    <%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>--%>

    <spring:url value="/resources/theme1/css/bootstrap.min.css" var="bootstrapcss"/>
    <link href="<c:url value="${bootstrapcss}"/>" rel=stylesheet >

</head>
<body style="background-color: lightcyan;">
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/start">Quiz System</a>
        </div>
        <div class="nav navbar-nav" style="float: right;">
            <li class="active"><a href="#">User: ${user.nickname}</a> </li>
            <li><a href="#">Logout</a> </li>
        </div>

    </div>
</nav>

<div class="container">
<div class="row">

    <div class="col-md-2">
        <div class="container-fluid right-container bg-info" style="border-radius: 10px;">

            <p class="card-title">Question</p>

            <div class="card-text">#${qnum+1} from ${qnums}</div>
        </div>
    </div>
    <div class="col-md-10" style="">
        <div class="container-fluid" style="background-color: darkcyan; color: white; border-radius: 10px;">

            <div class="row">
                <div class="col-md-4">
                    <div class="container-fluid"><h3>Quiz:${quiz.name}</h3></div>
                </div>

                <div class="col-md-4 col-md-offset-4">
                    <div  class="container-fluid" style="background: white; width: 10rem;">
                        <c:if test="${not empty quiz.picture}">
                            <img src="/resources/images/thumb/quiz/${quiz.id}/${quiz.picture}">
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</div>


<form action="/quiz" method="post">
    <input type="hidden" name="quizid" value="${quiz.id}"/>
    <input type="hidden" name="userid" value="${user.id}"/>
    <input type="hidden" name="qnum" value="${qnum}"/>
    <input type="hidden" name="sessionid" value="${sessionid}"/>
<br>

    <div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-6">
            <div class="container-fluid bg-info" style="border-radius: 10px;">
                <h4 class="card-title">${question.text}</h4>
            </div>
        </div>
        <div class="col-md-4">
            <div class="container-fluid" >
                <c:if test="${not empty question.picture}">
                    <a href="/resources/images/quiz/${quiz.id}/questions/${question.id}/${question.picture}"> <img style="border-radius: 10px;" src="/resources/images/thumb/quiz/${quiz.id}/questions/${question.id}/${question.picture}"></a>
                </c:if>
            </div>
        </div>
    </div>
    </div>


    <div class="container">
        <div class="row">

            <div class="col-md-offset-2 col-md-10">
            <div class="container-fluid" style="background-color: white; color: darkcyan; border-radius: 10px;">
            <c:forEach var="opt" items="${question.options}">
                <div class="list-group list-group-flush">
                    <label>
                        <input class="list-group-item-info" type="radio" name="option" value=${opt.id}><c:out value="${opt.text}"/>
                    </label>
                </div>
            </c:forEach>
            </div>
            </div>
        </div>
    </div>

    <p>

    <div class="container" style="background-color: darkcyan; padding-top: 10px; text-align: right;">
        <div class="btn-group-md">

        <c:if test="${qnum > 0}">
            <button class="btn btn-default" style="color: darkcyan" type="submit" formaction="/quiz?userid=${user.id}&quizid=${quiz.id}&questionid=${qnum-1}" name="button" value="prevQuestion">
                <%--<button type="submit" name="questionid" value="${qnum-1}">--%>
                        PREV</button>
            &nbsp;&nbsp;
        </c:if>


        <c:if test="${qnum < qnums-1}">
            <button class="btn btn-default" style="color: darkcyan" type="submit" formaction="/quiz?userid=${user.id}&quizid=${quiz.id}&questionid=${qnum+1}" name="button" value="nextQuestion">
                <%--<button type="submit" name="questionid" value="${qnum+1}">--%>
                NEXT</button>
            &nbsp;&nbsp;
        </c:if>

        <button class="btn btn-info" type="submit" name="button" value="finish">FINISH</button></p>

        </div>
    </div>
</form>

</body>
</html>
