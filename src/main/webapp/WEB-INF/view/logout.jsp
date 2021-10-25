<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>
<c:url var="logoutUrl" value="/j_spring_security_logout"/>
<form action="${logoutUrl}" method="post">
    <button>logout</button>
</form>
</body>
</html>
