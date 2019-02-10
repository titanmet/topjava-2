<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<c:url var="addUrl" value="/meals/add" />
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<nav>
    <table border="1">
        <thead>
        <tr>
            <th> ДАТА </th>
            <th> ОПИСАНИЕ </th>
            <th> КАЛОРИИ </th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${mealsWithExceed}" var="meal">
            <c:url var="editUrl" value="/meals?action=edit&id=${meal.getId()}" />
            <c:url var="deleteUrl" value="/meals?action=delete&id=${meal.getId()}" />
            <tr style="color: ${!meal.excess ? '#00ff00 ': '#ff0000 '};">
                <td><c:out value="${meal.getDateTime().toString().replace('T',' ')}"/></td>
                <td><c:out value="${meal.getDescription()}"/></td>
                <td><c:out value="${meal.getCalories()}"/></td>

                <td><a href="${editUrl}">Edit</a></td>
                <td><a href="${deleteUrl}">Delete</a></td>
            </tr>
        </c:forEach>
        <c:url var="addUrl" value="/meals?action=edit&id=0" />
        <td><a href="${addUrl}">Add new meal</a></td>
        </tbody>
    </table>
</nav>
</body>
</html>