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

    <spring:url value="/resources/theme1/css/bootstrap.min.css" var="maincss"/>
    <link href="<c:url value="${maincss}"/>" rel=stylesheet >

</head>
<body style="background-color: lightcyan">
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <h4><a href="#">Quiz System</a></h4>
        </div>
        <div class="nav navbar-nav">
            <li class="active"><a href="#">User: ${user.nickname}</a> </li>
            <li><a href="#">Выйти</a> </li>
        </div>

    </div>
    <div class="container" style="background-color: darkcyan; color: white">
        <h2>Quiz:${quiz.name}</h2>
        <p>Question #${qnum+1} from ${qnums}</p>
    </div>
</nav>

    <div class="container" style="background-color: white; color: darkcyan">
        <h3>${question.text}</h3>
    </div>


<form action="/quiz" method="post">
    <input type="hidden" name="quizid" value="${quiz.id}"/>
    <input type="hidden" name="userid" value="${user.id}"/>
    <input type="hidden" name="qnum" value="${qnum}"/>
    <input type="hidden" name="sessionid" value="${sessionid}"/>
<br>
    <div class="container" style="background-color: darkcyan; color: white">

            <c:forEach var="opt" items="${question.options}">
                <input type="radio" name="option" value=${opt.id}><c:out value="${opt.text}"/>
                <br>
            </c:forEach>

    </div>
    <p>

    <div class="container">
    <div class="btn-group-md">

        <c:if test="${qnum > 0}">
            <button class="btn btn-default" style="color: darkcyan" type="submit" formaction="/quiz?userid=${quiz.id}&quizid=${quiz.id}&questionid=${qnum-1}" name="button" value="prevQuestion">
                <%--<button type="submit" name="questionid" value="${qnum-1}">--%>
                        PREV</button>
            &nbsp;&nbsp;
        </c:if>


        <c:if test="${qnum < qnums-2}">
            <button class="btn btn-default" style="color: darkcyan" type="submit" formaction="/quiz?userid=${quiz.id}&quizid=${quiz.id}&questionid=${qnum+1}" name="button" value="nextQuestion">
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
