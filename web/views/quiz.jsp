<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quiz </title>
</head>
<body>
<p>Test system</p>

<br>
<%--Test: ${quiz.name}--%>
<br>
Question numbers: ${questionList.size()}
<br>
<c:forEach var="question" items="${questionList}" varStatus="theCount1">
    <c:out value="${theCount1.count}"/>)<c:out value="${question.getText()}"/>
    <br>
    <c:forEach var="opt" items="${question.options}" varStatus="theCount">
        -&nbsp;  <c:out value="${opt.text}"/>
        <br>
    </c:forEach>
</c:forEach>
</body>
</html>
