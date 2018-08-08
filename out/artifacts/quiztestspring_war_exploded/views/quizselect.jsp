<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of quiz</title>
    <spring:url value="/resources/theme1/css/main.css" var="maincss"/>
    <%--<spring:url value="/resources/theme1/css/bootstrap.min.css" var="maincss"/>--%>
    <link href="<c:url value="${maincss}"/>" rel=stylesheet >

</head>

<body>

    <form action="/quiz">

    <section id="user">


            <div class="title">
                <p>QUIZ SYSTEM</p>
            </div>
        <div class="mainblock">
            <div id="select_user">
                <select name="userid">
                    <c:forEach var="user" items="${users}" varStatus="theCount">
                        <c:choose>
                            <c:when test="${theCount.count ge 2}">
                                <option value="${user.id}"><c:out value="${theCount.count}"/>. <c:out value="${user.firstname}"/>&nbsp;<c:out value="${user.lastname}"/></option>
                            </c:when>
                            <c:otherwise>
                                <option selected value="${user.id}"><c:out value="${theCount.count}"/>. <c:out value="${user.firstname}"/>&nbsp;<c:out value="${user.lastname}"/></option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                &nbsp;&nbsp;
                <button class="btn btn-success" type="submit" formaction="/statistic/user">STATISTICS</button>
            </div>
        </div>
    </section>

    <section id="quiz">

        <div class="select_title">
        <p>Select quiz:</p>
        </div>

        <div class="mainblock">
        <div id="select_quiz">
        <br>
        <c:forEach var="quiz" items="${quizs}" varStatus="theCount1">
            <c:choose>
            <c:when test="${theCount1.count ge 2}">
            <input type="radio" name="quizid" value=${quiz.id}>
            <c:out value="${theCount1.count}"/>)<c:out value="${quiz.name}"/><br>
            </c:when>
            <c:otherwise>
                <input type="radio" checked name="quizid" value=${quiz.id}>
                <c:out value="${theCount1.count}"/>)<c:out value="${quiz.name}"/><br>
            </c:otherwise>
            </c:choose>

        </c:forEach>
        </div>
        </div>
    </section>

        <section id="bottom_buttons">
            <div class="bottom_buttons">
                <div class="mainblock">
                    <button type="submit" name="action" value="startQuiz">Test RUN!</button>
                </div>
            </div>
        </section>
    </form>


</body>
</html>