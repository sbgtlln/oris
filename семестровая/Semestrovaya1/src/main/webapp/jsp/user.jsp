<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" href="style/bootstrap.min.css">
</head>
<body>
<header>
    <h1>Личный кабинет</h1>
</header>

<section>
    <h2>Профиль пользователя</h2>
    <p>ID: ${user.id}</p>
    <p>Имя пользователя: ${user.username}</p>
    <!-- Дополнительная информация о пользователе, если необходимо -->
</section>

<footer>
    <p>&copy; 2023 FITSET</p>
</footer>
</body>
</html>
