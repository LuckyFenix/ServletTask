package com.luckyfenix.help;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LuckyFenix on 07.07.2014.
 */
public class DBC
{
    private static String dataBaseName = "my.db";
    private static Connection con;
    private static Statement st;

    private DBC(){}

    private static void init() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:" + dataBaseName);
        st = con.createStatement();
    }

    public static void execute(String query) throws SQLException, ClassNotFoundException
    {
        init();
        st.execute(query);
        close();
    }

    public static void executeUpdate(String query) throws SQLException, ClassNotFoundException
    {
        init();
        st.executeUpdate(query);
        close();
    }

    public static List<Map> executeQuery(String query) throws SQLException, ClassNotFoundException
    {
        init();
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<Map> list = new ArrayList<>();
        while (resultSet.next())
        {
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++)
            {
                map.put(resultSet.getMetaData().getColumnLabel(i + 1),
                        resultSet.getString(i + 1));
            }
            list.add(map);
        }
        resultSet.close();
        close();
        return list;
    }

    public static void close() throws SQLException
    {
        st.close();
        con.close();
    }
}
