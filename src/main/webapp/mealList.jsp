<%--
  Created by IntelliJ IDEA.
  User: Aspire
  Date: 06.12.2016
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@taglib uri="functions.tld" prefix="f" %>
<html>
<head>
    <link rel="stylesheet" href="resources/style.css">
    <title>Meal List Title</title>
</head>
<body>
    <h2>Meal List Body</h2>

    <c:if test="${!empty mealList}">
        <table>
            <tr>
                <th width="80">Meal ID</th>
                <th width="120">Description</th>
                <th width="120">Calories</th>
                <%--<th width="60">Exceed</th>--%>
                <th width="120">Date and Time</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach var="meal" items="${mealList}">
                <%--<jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>--%>
                <tr class=<c:out value="${meal.exceed ? 'exceeded': 'normal'}"/>>
                    <td>${meal.id}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <%--<td>${meal.exceed}</td>--%>
                    <td>${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm:ss')}</td>
                    <%--<td><fmt:formatDate value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
                    <td><a href="<c:url value='?edit=${meal.id}' />" >Edit</a></td>
                    <td><a href="<c:url value='?remove=${meal.id}' />" >Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

<br/>
<br/>
<h3><a href="<c:url value='?new' />" >Add Meal</a></h3>
</body>
</html>
