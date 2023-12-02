<%--
  Created by IntelliJ IDEA.
  User: hamst
  Date: 30.11.2023
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trainings</title>
</head>
<body>

<h2>Existing Trainings</h2>
<ul>
    <c:forEach var="training" items="${trainings}">
        <li>${training.title} - ${training.description}</li> 
    </c:forEach>
</ul>


<h2>Add a New Training</h2>
    <form action="${pageContext.request.contextPath}/jsp/training.jsp" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea>
        <br>
        <input type="submit" value="Add Training">
    </form>



</body>
</html>
