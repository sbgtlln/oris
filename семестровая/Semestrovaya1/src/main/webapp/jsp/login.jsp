<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
  <h2>Login</h2>
  <% String error = (String)request.getAttribute("error"); %>
  <% if (error != null) { %>
    <p style="color: red;"><%= error %></p>
  <% } %>
  <form action="main.jsp" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit">Login</button>
  </form>
</body>
</html>
