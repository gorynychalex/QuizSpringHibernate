<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Option for Question</title>
</head>
<body>

<p>Test: ${quiz.name}</p>

<p>Question: ${question.text}</p>


<c:if test="${not empty optionattr.text}">
    <c:url var="urlaction" value="/quiz/${quiz.id}/question/${question.id}/option/edit"/>
    <c:set var="buttonText" value="Edit"/>
</c:if>
<c:if test="${empty optionattr.text}">
    <c:url var="urlaction" value="/quiz/${quiz.id}/question/${question.id}/option/add"/>
    <c:set var="buttonText" value="Add"/>
</c:if>

<p>Add Option:</p>
<form:form modelAttribute="optionattr" method="post" action="${urlaction}">
    <form:hidden path="id"/>
    <form:label path="text">Option Text:</form:label>
    <form:input path="text" />
    <br>
    <form:label path="correct">Correct:</form:label>
    <form:select path="correct">
        <form:option value="true"/>
        <form:option value="false"/>
    </form:select>
    <br>
    <form:label path="picture">picture url:</form:label>
    <form:input path="picture" />
    <br>
    <input type="submit" value="${buttonText}"/>
</form:form>



</body>
</html>
