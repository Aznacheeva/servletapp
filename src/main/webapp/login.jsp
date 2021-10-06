<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <form action = "/login" method = "post">
            Логин: <input type="text" name="login"/>
            <br>
            Пароль: <input type="password" name="password"/>
            <br>
            <input type="submit" value="Войти">
        </form>
        <form action="/registration">
            <input type="submit" value="Регистрация">
        </form>
    </body>
</html>