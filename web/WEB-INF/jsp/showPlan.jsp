<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: imran.azad
  Date: 5/21/14
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Meal Plan</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
    <table id="mealPlan" border="1px">
        <h1>General Plan</h1>
        <tr>
            <td>
                Day
            </td>
            <td>
                Breakfast
            </td>
            <td>
                Lunch
            </td>
        </tr>
        <c:forEach var="mealPlan" items="${requestScope.totalMealPlan}">
            <c:choose>
                <c:when test="${sessionScope.authenticatedUser.isAdmin()==true}">
                    <tr>
                        <td>
                            <c:out value="${mealPlan.getNameOfDay()}"></c:out>
                        </td>
                        <td>
                            <c:url var="updateData" value="updatePlan">
                                <c:param name="nameOfDay" value="${mealPlan.getNameOfDay()}"/>
                                <c:param name="mealType" value="breakfast"/>
                                <c:param name="menu" value="${mealPlan.getBreakfastMenu()}"/>
                            </c:url>
                            <a href="<c:out value="${updateData}" />">
                                <c:out value="${mealPlan.getBreakfastMenu()}"></c:out>
                            </a>
                        </td>
                        <td>
                            <c:url var="updateData" value="updatePlan">
                                <c:param name="nameOfDay" value="${mealPlan.getNameOfDay()}"/>
                                <c:param name="mealType" value="lunch"/>
                                <c:param name="menu" value="${mealPlan.getLunchMenu()}"/>
                            </c:url>
                            <a href="<c:out value="${updateData}" />">
                                <c:out value="${mealPlan.getLunchMenu()}"></c:out>
                            </a>
                        </td>
                    </tr>
                </c:when>

                <c:otherwise>
                    <tr>
                        <td>
                            <c:out value="${mealPlan.getNameOfDay()}"></c:out>
                        </td>
                        <td>
                            <c:out value="${mealPlan.getBreakfastMenu()}"></c:out>
                        </td>
                        <td>
                            <c:out value="${mealPlan.getLunchMenu()}"></c:out>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>

    </br>
    <a href="<c:url value="/logout"/>">Logout</a>
</body>
</html>