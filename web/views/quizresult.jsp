<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gorynych
  Date: 21.03.17
  Time: 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result of quiz</title>
</head>
<body>

<p>RESULTS:</p>

<c:forEach var="mark" items="${marks}" varStatus="count">
    ${count.count}) ${mark}<br>
</c:forEach>

<p>Entirely: ${markfull}</p>

</body>
</html>
