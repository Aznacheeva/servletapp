<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Регистрация</title>
    </head>
    <body>
    <form method="post" action="/registration">
        <b>Придумайте логин</b>
        <br>
        <input name = "login">
        <br>
        <b>Придумайте пароль</b>
        <br>
        <input type ="password" name = "password">
        <br>
        <b>Введите email</b>
        <br>
        <input name = "email">
        <p><input type="submit" value="Создать аккаунт"></p>
        ${message}
    </form>
    </body>
</html>