<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Регистрация</title>
    </head>
    <body>
    <form method="post" action="/registration">
        Придумайте логин
        <input name = "login">
        <br>
        Придумайте пароль
        <input type ="password" name = "password">
        <br>
        Введите email
        <input name = "email">
        <p><input type="submit" value="Создать аккаунт"></p>
        ${message}
    </form>
    </body>
</html>