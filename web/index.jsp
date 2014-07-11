<%@ page import="com.luckyfenix.entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: LuckyFenix
  Date: 07.07.2014
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Привет</title>
</head>
<body>

<%! User user; %>
<%
    user = (User) session.getAttribute("user");
    if (user == null)
    {
%>
<p>
    <a href="login.jsp">Войти</a>
</p>
<p>
    <a href="registration.jsp">Зарегистрироватся</a>
</p>
<%
    } else
    {
        out.println("<p>");
        out.println("Логин: " + user.getLogin());
        out.println("</p>");
        out.println("<p>");
        out.println("Email: " + user.getEmail());
        out.println("</p>");
        if (user.isAdmin())
        {
%>

<p>
<form action="user_list.jsp" method="get">
    <input type="submit" value="Список пользователей">
</form>
</p>

<%
        }
%>

<p>
<form action="logout" method="post">
    <input type="submit" value="Выйти">
</form>
</p>

<%
    }
%>

</body>
</html>
