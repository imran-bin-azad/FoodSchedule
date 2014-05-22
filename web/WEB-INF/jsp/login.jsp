<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: imran.azad
  Date: 5/18/14
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
    <form method="POST" action="<c:url value="/login"/>">
        Handle: <input type="text" name="handle"> </br>
        Password: <input type="password" name="password">
        <input type="submit" value="Login">
    </form>

    <div id="message">
        <c:if test="${not empty message}">
            <p1>${message}</p1>
        </c:if>
    </div>
</body>
</html>