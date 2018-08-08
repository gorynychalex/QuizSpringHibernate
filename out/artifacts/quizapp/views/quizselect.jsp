<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of quiz</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--<spring:url value="/resources/theme1/css/main.css" var="maincss"/>--%>
    <spring:url value="/resources/theme1/css/bootstrap.min.css" var="bootstrapcss"/>
    <link href="<c:url value="${bootstrapcss}"/>" rel=stylesheet >

</head>

<body style="background-color: lightcyan;">

<nav class="navbar navbar-default navbar-expand">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/start">Quiz System</a>
        </div>
        <div class="nav navbar-nav" style="float: right;">
            <li class="active"><a href="#">User: ${user.nickname}</a> </li>
            <li><a href="#">Выйти</a> </li>
        </div>
    </div>
</nav>


    <form action="/quiz">

        <div class="container" style="background-color: darkcyan; color: white;">
            <h2>Select user: </h2>

            <section id="user">

                <div class="radio" style="color: darkcyan;">

                    <div id="select_user" class="input-group-prepend">
                        <select name="userid">
                            <c:forEach var="user" items="${users}" varStatus="theCount">
                                <c:choose>
                                    <c:when test="${theCount.count ge 2}">
                                        <option value="${user.id}"><c:out value="${theCount.count}"/>. <c:out value="${user.firstname}"/>&nbsp;<c:out value="${user.lastname}"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <c:out value="${theCount.count}"/>. <c:out value="${user.firstname}"/>&nbsp;<c:out value="${user.lastname}"/>
                                        </button>
                                        <option selected value="${user.id}"><c:out value="${theCount.count}"/>. <c:out value="${user.firstname}"/>&nbsp;<c:out value="${user.lastname}"/></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>

                    </div>
                </div>
                <button class="btn btn-info pull-right" type="submit" formaction="/statistic/user">USER STATISTICS</button>
            </section>
        </div>



    <section id="quiz">

        <div class="container" style="color: darkcyan; ">
        <%--<div class="mainblock">--%>

            <div class="row">
                <div class="col-md-4"><h4>Select quiz:</h4></div>
                <div class="col-md-4"></div>
                <div class="col-md-4"></div>
            </div>


            <div id="select_quiz" class="radio">
            <br>
                <c:forEach var="quiz" items="${quizs}" varStatus="theCount1">
                    <%--<c:out value="${theCount1.count}"/>--%>
                    <c:choose>
                        <c:when test="${theCount1.count ge 2}">
                            <input type="radio" name="quizid" value=${quiz.id}>
                            <c:out value="${quiz.name}"/><br>
                        </c:when>
                        <c:otherwise>
                            <input type="radio" checked name="quizid" value=${quiz.id}>
                            <c:out value="${quiz.name}"/><br>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </div>
        </div>
    </section>

        <%--<section id="bottom_buttons">--%>
            <%--<div class="bottom_buttons">--%>
                <%--<div class="title">--%>
                    <%--<button type="submit" name="action" value="startQuiz">Test RUN!</button>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</section>--%>
        <div class="container" style="background-color: darkcyan; padding-top: 10px; text-align: right;">
            <button class="btn btn-info" type="submit" name="action" value="startQuiz">TEST RUN!</button></p>
        </div>
    </form>

</body>

<%--<script src="/resources/theme1/js/main.js"></script>--%>
</html>