package com.luckyfenix.help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBI
{
    private Connection bd;
    private Statement st;
    private String dbName;
    private String url;

    public DBI()
    {
        this.dbName = "dbfortask";

        Properties connInfo = new Properties();

        connInfo.put("user", "root");
        connInfo.put("password", "root");
        connInfo.put("charSet", "UTF8");
        try
        {

            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://" + "localhost" + "/" + dbName + "?useUnicode=true&characterEncoding=utf8";
            bd = DriverManager.getConnection(url, connInfo);
            st = bd.createStatement();
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        } catch (SQLException e)
        {
            try
            {
                url = "jdbc:mysql://" + "localhost"/*properties.getProperty("DataBaseHost")*/ + "/information_schema";
                bd = DriverManager.getConnection(url, connInfo);
                st = bd.createStatement();
                st.execute("CREATE DATABASE " + dbName + " CHARACTER SET utf8 COLLATE utf8_general_ci;");
                url = "jdbc:mysql://" + "localhost"/*properties.getProperty("DataBaseHost")*/ + "/" + dbName + "?useUnicode=true&characterEncoding=utf8";
                bd = DriverManager.getConnection(url, connInfo);
                st = bd.createStatement();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    public void close()
    {
        try
        {
            bd.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getBd() {
        return bd;
    }

    public Statement getSt() {
        return st;
    }

    public String getDbName() {
        return dbName;
    }
}
