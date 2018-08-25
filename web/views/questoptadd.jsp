<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Option for Question</title>
    <spring:url value="/resources/theme1/css/bootstrap.min.css" var="maincss"/>
    <link href="<c:url value="${maincss}"/>" rel=stylesheet >
    <link href="/resources/theme1/css/main.css" rel="stylesheet">
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
    <form:hidden id="fileurl" path="picture"/>
    <br>
    <input id="fileupload" type="hidden" name="filesmall"/>
    <br>
    <input id="filethumb" type="hidden" name="thumb"/>
    <br>
    <input type="submit" value="${buttonText}"/>
</form:form>


<input id="inputimgprev" type="file" name="file" onchange="previewFile()"/>

<br>

<div class="itemform">
    <div id="imageprevie">
        <p>Question preview:</p>
        <p>Question:  <span id="outtext"></span> </p>
        <br>

        <img src="" id="previewthumb" alt="Image thumb preview ">
        <%--<br>--%>
        <%--<img src="" id="preview" width="150" alt="Image preview">--%>
        <%--<input type="file" id="inputimgprev" onchange="previewFile()">--%>
    </div>

</div>


<script src="<c:url value="/resources/theme1/js/upload.js"/>"></script>



</body>
</html>
