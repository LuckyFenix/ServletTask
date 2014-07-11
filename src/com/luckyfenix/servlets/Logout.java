package com.luckyfenix.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LuckyFenix on 11.07.2014. ${TEMP}
 */
public class Logout extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        request.getSession().setAttribute("user", null);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
