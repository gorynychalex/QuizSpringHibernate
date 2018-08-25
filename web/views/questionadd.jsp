<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add the question</title>
    <meta charset="UTF-8">
    <spring:url value="/resources/theme1/css/bootstrap.min.css" var="bootstrapcss"/>
    <link href="<c:url value="${bootstrapcss}"/>" rel=stylesheet >
    <%--<script src="<c:url value="/resources/theme1/js/main.js"/> "></script>--%>
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

    <div class="question">
    <div class="itemform">
        <form:form id="formadd" modelAttribute="questionattr" method="post" action="${urlaction}" enctype="multipart/form-data">
            <form:hidden path="id"/>
            <br>
            <form:label path="text">Question Text:</form:label>
            <form:input id="inputtext" path="text" cssStyle="color: #0f0f0f"/>
            <br>
            <%--<form:label path="picture">Picture url</form:label>--%>
            <form:hidden id="fileurl" path="picture"/>
            <%--<input type="hidden" id="filethumburl" name="thumburl"/>--%>
            <br>
            <%--<form:input path="picture" />--%>
            <%--<form:label id="pictureurl" path="pic"></form:label>--%>

            <br>
            <input id="fileupload" type="hidden" name="filesmall"/>
            <br>
            <input id="filethumb" type="hidden" name="thumb"/>
            <br>
            <input type="hidden" name="quizId" value="${quiz.id}"/>
            <input type="submit" value="${buttonText}" style="color: darkcyan"/>
        </form:form>

        <%--<input type="file" id="fileupload">--%>

    </div>
        <input id="inputimgprev" type="file" name="file" onchange="previewFile()"/>

    <%--<form:form id="formaddfile" method="post" action="/file/upload1" enctype="multipart/form-data">--%>
        <%--<input type="hidden" name="quizId" value="${quiz.id}"/>--%>
        <%--<input type="file" name="file" />--%>
        <%--<input type="submit" value="Submit"/>--%>
    <%--</form:form>--%>

    <div class="itemform">
        <div id="imageprevie">
            <p>Question preview:</p>
            <p>Question:  <span id="outtext"></span> </p>
            <%--<img src="" id="imgprev" alt="Image preview ...">--%>
            <br>
            <img src="" id="previewthumb" alt="Image preview ...">
            <%--<br>--%>
            <%--<img src="" id="fileupload" alt="Image upload ">--%>
            <%--<input type="file" id="inputimgprev" onchange="previewFile()">--%>
        </div>

    </div>
    </div>
    <div id="log">Load process</div>
</section>


<script src="<c:url value="/resources/theme1/js/upload.js"/>"></script>
<%--<script src="<c:url value="/resources/theme1/js/main.js"/>"></script>--%>

</body>
</html>