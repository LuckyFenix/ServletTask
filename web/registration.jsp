<%--
  Created by IntelliJ IDEA.
  User: LuckyFenix
  Date: 09.07.2014
  Time: 2:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
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
            out.println(error);
            out.println("</font>");
            out.println("</p>");
        }
    %>
</p>

<form action="registration_success" method="post">
    <p>
        <label>Логин:</label>
        <input type="text" name="login">
    </p>
    <p>
        <label>Email:</label>
        <input type="text" name="email">
    </p>
    <p>
        <label>Пароль:</label>
        <input type="password" name="password">
    </p>
    <p>
        <label>Повторить пароль:</label>
        <input type="password" name="confirm_password">
    </p>
    <p>
        <button type="submit">Зарегистрироватся</button>
    </p>
</form>

</body>
</html>
