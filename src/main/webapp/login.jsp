<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Вход</title>
    </head>
    <body>
        <form action = "/login" method = "post">
            <b>Логин</b>
            <br>
            <input type="text" name="login"/>
            <br>
            <b>Пароль</b>
            <br>
            <input type="password" name="password"/>
            <br>
            <input type="submit" value="Войти">
            <br>
        </form>
        <form action="/registration">
            <input type="submit" value="Регистрация">
        </form>
    </body>
</html>