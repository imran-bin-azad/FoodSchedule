<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: imran.azad
  Date: 5/22/14
  Time: 9:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title></title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
    <form action="<c:url value="/updatePlan"/>" method="POST">
        ${param.nameOfDay}'s ${param.mealType} </br>

        <input type="hidden" name="nameOfDay" value="${param.nameOfDay}"> </br>
        <input type="hidden" name="mealType" value="${param.mealType}"> </br>
        <input type="text" name="newMenu" value="${param.menu}"> </br>
        <input type="submit" value="Update">
    </form>

    </br>
    <a href="<c:url value="/logout"/>">Logout</a>
</body>
</html>