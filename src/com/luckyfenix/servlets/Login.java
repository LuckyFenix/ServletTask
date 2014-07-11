package com.luckyfenix.servlets;

import com.luckyfenix.entity.User;
import com.luckyfenix.help.DBI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by LuckyFenix on 09.07.2014. ${TEMP}
 */
public class Login extends HttpServlet
{
    private Statement st;
    private ResultSet rs;
    private DBI dbi;

    @Override
    public void init() throws ServletException
    {
        try
        {
            dbi = new DBI();
            st = dbi.getSt();
            st.execute("CREATE TABLE IF NOT EXISTS `users` (\n" +
                    "  `id` int(5) NOT NULL AUTO_INCREMENT,\n" +
                    "  `login` text NOT NULL,\n" +
                    "  `password` text NOT NULL,\n" +
                    "  `email` text NOT NULL,\n" +
                    "  `reg_date` date NOT NULL,\n" +
                    "  `is_admin` tinyint(1) NOT NULL DEFAULT '0',\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;");
            st.close();
            dbi.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            DBI dbi = new DBI();
            st = dbi.getSt();
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("utf8");
            PrintWriter out = response.getWriter();
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            rs = st.executeQuery("select * from users " +
                    "where " +
                    "login='" + login + "' " +
                    "and " +
                    "password='" + password + "';");
            if (rs.next())
            {
                request.getSession().setAttribute("user", new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDate("reg_date"),
                        rs.getBoolean("is_admin")));
                out.println("<html><head>");
                out.println("<title>Успех</title>");
                out.println("</head><body>");
                out.println("Успешная логинизаци. Перейти на <a href=\"index.jsp\">главную?</a>");
                out.println("</body>");
            } else
            {
                request.setAttribute("error", "Неверный логин или пароль!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy()
    {
        try
        {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (dbi != null)
                dbi.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
