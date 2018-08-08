<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add quiz</title>
</head>
<body>

<p>Create new QUIZ:</p>

<form:form modelAttribute="quizattr" method="post" action="/quiz/add">
    <form:label path="name">Quiz name:</form:label>
    <form:input id="inputtext" path="name" cssStyle="color: #0f0f0f"/>
    <%--<form:input path="name" />--%>
    <%--<br>--%>
    <%--<form:label path="picture">picture url:</form:label>--%>
    <%--<form:input path="picture" />--%>
    <br>
    <form:hidden id="fileurl" path="picture"/>
    <br>
    <input id="inputimgprev" type="file" name="file" onchange="previewFile()"/>
    <br>
    <input id="fileupload" type="hidden" name="filesmall"/>
    <br>
    <input id="filethumb" type="hidden" name="thumb"/>
    <br>
    <input type="submit" value="Add"/>
</form:form>

<br>

<div class="itemform">
    <div id="imageprevie">
        <p>Question preview:</p>
        <p>Question:  <span id="outtext"></span> </p>
        <%--<img src="" id="imgprev" alt="Image preview ...">--%>
        <br>
        <img src="" id="imgprevsmall" alt="Image preview ...">
        <%--<br>--%>
        <%--<img src="" id="fileupload" alt="Image upload ">--%>
        <%--<input type="file" id="inputimgprev" onchange="previewFile()">--%>
    </div>

</div>


<script src="<c:url value="/resources/theme1/js/upload.js"/>"></script>

</body>
</html>
