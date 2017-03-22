<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>The list of quiz</title>
</head>
<body>

<form action="/quiz">

<p>Choose your name: </p>

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


<p>List of tests:</p>
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

    <button type="submit" name="action" value="startQuiz">Test RUN!</button>
</form>

</body>
</html>
