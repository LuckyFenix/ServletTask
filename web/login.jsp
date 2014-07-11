<%--
  Created by IntelliJ IDEA.
  User: LuckyFenix
  Date: 09.07.2014
  Time: 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Войти</title>
</head>
<body>
<%! String error; %>

<p>
    <a href="index.jsp">Вернутся на главную.</a>
</p>

<p>
    <%
        error = (String) request.getAttribute("error");
        if (error != null)
        {
            out.println("<p>");
            out.println("<font color=\"red\">");
            out.println("Ошибка. " + error);
            out.println("</font>");
            out.println("</p>");
        }
    %>
</p>
<form action="login_success" method="post">
    <p>
        <label>Логин:</label>
        <input type="text" name="login">
    </p>
    <p>
        <label>Пароль:</label>
        <input type="password" name="password">
    </p>
    <p>
        <button type="submit">Войти</button>
    </p>
</form>

</body>
</html>
