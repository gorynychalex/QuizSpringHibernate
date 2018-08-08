<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add the question</title>
    <script src="<c:url value="/resources/theme1/js/main.js"/> "></script>
    <link href="/resources/theme1/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/resources/theme1/css/main.css" rel="stylesheet">
</head>

<body>

<div class="header">
    <div class="wrapper">
        <p>Test: ${quiz.name}</p>
        <p>Add the question:</p>
    </div>
</div>

<c:if test="${not empty questionattr.text}">
    <c:url var="urlaction" value="/quiz/${quiz.id}/question/edit"/>
    <c:set var="buttonText" value="Edit"/>
</c:if>
<c:if test="${empty questionattr.text}">
    <c:url var="urlaction" value="/quiz/${quiz.id}/question/add"/>
    <c:set var="buttonText" value="Add"/>
</c:if>

<c:if test="${not empty picurl}">
    <c:url var="picurl" value="${picurl}"/>
</c:if>

<section id="content">

    <div class="itemform">
        <form:form id="formadd" modelAttribute="questionattr" method="post" action="${urlaction}">
            <form:hidden path="id"/>
            <br>
            <form:label path="text">Question Text:</form:label>
            <form:input id="inputtext" path="text" />
            <br>
            <%--<form:label  path="picture">Picture url</form:label>--%>
            <form:hidden id="fileurl" path="picture"/>
            <%--<form:input path="picture" />--%>
            <%--<form:label id="pictureurl" path="pic"></form:label>--%>
            <input id="inputimgprev" type="file" onchange="previewFile()">
            <input type="hidden" name="file" id="file">
            <br>
            <input type="hidden" name="quizId" value="${quiz.id}"/>
            <input type="submit" value="${buttonText}"/>
        </form:form>

        <%--<input type="file" id="fileupload">--%>

    </div>
    <%--<form:form id="formaddfile" method="post" action="/file/upload1" enctype="multipart/form-data">--%>
        <%--<input type="hidden" name="quizId" value="${quiz.id}"/>--%>
        <%--<input type="file" name="file" />--%>
        <%--<input type="submit" value="Submit"/>--%>
    <%--</form:form>--%>

    <div class="itemform">
        <div id="imageprevie">
            <p>Question preview:</p>
            <p>Question:  <span id="outtext"></span> </p>
            <img src="" id="imgprev" height="200" alt="Image preview ...">
            <%--<input type="file" id="inputimgprev" onchange="previewFile()">--%>
        </div>

    </div>
</section>

<div id="log">Load process</div>

<script src="<c:url value="/resources/theme1/js/upload.js"/>"></script>

</body>
</html>