<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="vh-100 d-flex justify-content-center align-items-center">
    <div class="container">
        <div class="row d-flex justify-content-center">
            <div class="col-12 col-md-8 col-lg-6">
                <div class="card bg-white">
                    <div class="card-body p-5">
                        <form class="mb-3 mt-md-4" method="post" action="register">
                            <h2 class="fw-bold mb-2 text-uppercase ">Мой сайт</h2>
                            <p class="mb-5">Пожалуйста, введите свои данные для регистрации!</p>
                            <div class="mb-3">
                                <label for="firstName" class="form-label ">Имя</label>
                                <input type="text" class="form-control" id="firstName" name="firstName"
                                       placeholder="Имя">
                            </div>
                            <div class="mb-3">
                                <label for="secondName" class="form-label ">Фамилия</label>
                                <input type="text" class="form-control" id="secondName" name="secondName"
                                       placeholder="Фамилия">
                            </div>
                            <div class="mb-3">
                                <label for="username" class="form-label ">Имя пользователя</label>
                                <input type="text" class="form-control" id="username" name="username"
                                       placeholder="Имя пользователя">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label ">Пароль</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="*******">
                            </div>
                            <div class="d-grid">
                                <input class="btn btn-outline-dark" type="submit" value="Зарегистрироваться">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
