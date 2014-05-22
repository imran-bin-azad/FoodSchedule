<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: imran.azad
  Date: 5/18/14
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
    <c:if test="${not empty message}">
        <p1>${message}</p1>
    </c:if>

    </br>
    </br>

    <b>Today's Meal Plan</b>
    <table id="mealPlan" border="1px">
        <tr>
            <td>
                Breakfast
            </td>
            <td>
                Lunch
            </td>
        </tr>

        <tr>
            <td>
                <c:out value="${requestScope.breakfast}"></c:out>
            </td>
            <td>
                <c:out value="${requestScope.lunch}"></c:out>
            </td>
        </tr>
    </table>
    </br>
    <a href="<c:url value="/showPlan"/>">General Meal Plan</a>
    </br>
    <a href="<c:url value="/logout"/>">Logout</a>
</body>
</html>