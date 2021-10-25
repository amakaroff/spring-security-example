<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h1>Login</h1>
<c:if test="${param.error != null}">
    <p>Invalid username / password</p>
</c:if>
<c:url var="loginUrl" value="/j_spring_security_check"/>
<form action="${loginUrl}" method="post">
    <p><label for="username">User:</label></p>
    <input type="text" id="username" name="username"/>

    <p><label for="password">Password:</label></p>
    <input type="password" id="password" name="password">

    <div>
        <input name="submit" type="submit"/>
    </div>
</form>
</body>
</html>
