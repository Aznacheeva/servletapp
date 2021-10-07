<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <title>Файлы</title>
  </head>
  <body>
    <div>
        ${time}
    </div>
    <form action="/logout" method="get">
        <input type="submit" value="Выйти">
    </form>
    <h1>
        ${path}
        <hr>
    </h1>
    <div>
        <div>
            <a href="${parent}">
                📁 Вверх
            </a>
        </div>
        <table>
            <tr>
                <th>ФАЙЛ</th>
                <th>РАЗМЕР</th>
                <th>ДАТА</th>
            </tr>
            <c:forEach items="${list}" var="file">
            <tr>
                <td>
                    <a href=<c:out value="${file.getURL()}"/>>
                        <c:out value="${file.getFileName()}"/>
                    </a>
                </td>
                <td>
                    <c:out value="${file.getLength()}"/>
                </td>
                <td>
                    <c:out value="${file.getDate()}"/>
                </td>
                </c:forEach>
            </tr>
        </table>
    </div>
  </body>
</html>