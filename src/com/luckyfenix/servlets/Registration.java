package com.luckyfenix.servlets;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by LuckyFenix on 10.07.2014. ${TEMP}
 */
public class Registration extends HttpServlet
{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        PrintWriter out = response.getWriter();
        ArrayList<String> errorList = new ArrayList<>();

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        if (login.trim().equals(""))
        {
            errorList.add("Поле логин пусто");
        } else
        {
            try
            {
                DBI dbi = new DBI();
                st = dbi.getSt();
                rs = st.executeQuery("select * from users where login='" + login + "';");
                if (rs.next())
                {
                    errorList.add("Такой логин уже занят");
                }
                rs.close();
                st.close();
                dbi.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if (email.trim().equals(""))
        {
            errorList.add("Поле email пусто");
        } else
        {
            if (email.split("@").length != 2)
            {
                errorList.add("Неверный формат email");
            } else
            {
                try
                {
                    DBI dbi = new DBI();
                    st = dbi.getSt();
                    rs = st.executeQuery("select * from users where email='" + login + "';");
                    if (rs.next())
                    {
                        errorList.add("Такой email уже зарегистрирован");
                    }
                    rs.close();
                    st.close();
                    dbi.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        if (password.trim().equals(""))
        {
            errorList.add("Поле пароля пусто");
        } else
        {
            if (!password.equals(confirmPassword))
            {
                errorList.add("Пароли не совпадают");
            }
        }
        if (errorList.size() > 0)
        {
            String error = "";
            error += "<ul>\n";
            for (String e : errorList)
            {
                error += "<li>" + e + "</li>\n";
            }
            error += "</ul>";
            request.setAttribute("error", error);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        } else
        {
            try
            {
                DBI dbi = new DBI();
                st = dbi.getSt();
                st.execute("insert into users " +
                        "(email, login, password, reg_date) " +
                        "values " +
                        "('" + email + "', '" + login + "', '" + password + "', '" + dateFormat.format(new java.util.Date()) + "');");
                st.close();
                dbi.close();
                out.println("<html><head>");
                out.println("<title>Успех</title>");
                out.println("</head><body>");
                out.println("Успешная регистрация. Вернутся на <a href=\"index.jsp\">главную?</a>");
                out.println("</body>");
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
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
