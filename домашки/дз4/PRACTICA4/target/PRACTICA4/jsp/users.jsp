<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>Все пользователи сайта</h1>
<div>
    <table>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Имя пользователя</th>
        </tr>
                <%
                    List<User> users = (List<User>) request.getAttribute("usersForJsp");
                    for ( int i = 0; i < users.size(); i++) {
                    %>
                <tr>
                    <td> <%=users.get(i).getId()%></td>
                    <td> <%=users.get(i).getFirstName()%></td>
                    <td> <%=users.get(i).getSecondName()%></td>
                    <td> <%=users.get(i).getLogin()%></td>
                </tr>
                <%}%>
    </table>
</div>
</body>
</html>
