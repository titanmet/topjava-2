<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>MealPage</title>

</head>
<body>
<c:url var="addUrl" value="/meals/add" />
<h3><a href="index.html">Home</a></h3>
<h2>Create/edit meal</h2>
<nav>
    <form method="POST" action='meals' name="mealForm">
        <input type="hidden" name="mealid" readonly="readonly" id="id1"
               value="<c:out value="${meal.getId()}" />" />
        <div>
            <label>Описание</label>
            <div>
                <input type="text" name="description" id="id2"
                       value="<c:out value="${meal.getDescription()}" />" />
            </div>
        </div>
        <div>
            <label>Калории</label>
            <div>
                <input type="text" name="calories" id="id3"
                       value="<c:out value="${meal.getCalories()}" />" />
            </div>
        </div>
        <div>
            <label> Дата </label>
            <div>
                <input type="text"  name="datetime" id="id4"
                       value="<c:out value="${meal.getDateTime().toString().replace('T',' ')}" />" />
            </div>
        </div>
        <div>
            <div>
                <input type="submit" value="Submit" />
            </div>
        </div>

    </form>


</nav>
</body>
</html>