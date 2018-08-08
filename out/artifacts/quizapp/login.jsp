<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login page</title>
</head>
<body>

<form method="post" action="/j_spring_security_check">
    Username: <input type="text" name="j_username"/><br>
    Password: <input type="password" name="j_password" /><br>
    <input type="submit" value="login"/>
</form>

</body>
</html>
