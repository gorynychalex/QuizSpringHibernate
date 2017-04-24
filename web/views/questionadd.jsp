<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add the question</title>
</head>
<body>

<p>Test: ${quiz.name}</p>

<p>Add the question:</p>
<c:if test="${not empty questionattr.text}">
    <c:url var="urlaction" value="/quiz/${quiz.id}/question/edit"/>
    <c:set var="buttonText" value="Edit"/>
</c:if>
<c:if test="${empty questionattr.text}">
    <c:url var="urlaction" value="/quiz/question/add"/>
    <c:set var="buttonText" value="Add"/>
</c:if>

<form:form modelAttribute="questionattr" method="post" action="${urlaction}">
    <form:hidden path="id"/>
    <br>
    <form:label path="text">Question Text:</form:label>
    <form:input path="text" />
    <br>
    <form:label path="picture">picture url:</form:label>
    <form:input path="picture" />
    <br>
    <input type="hidden" name="quizId" value="${quiz.id}"/>
    <input type="submit" value="${buttonText}"/>
</form:form>
</body>
</html>
