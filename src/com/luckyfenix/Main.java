package com.luckyfenix;

import com.luckyfenix.help.DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Created by LuckyFenix on 07.07.2014.
 */
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:my.db");
            Statement st = con.createStatement();
            st.execute("DROP TABLE users;");
            st.close();
            con.close();
            DBC.execute("CREATE TABLE IF NOT EXISTS \"users\" (" +
                    "\"id\"  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "\"login\"  TEXT, " +
                    "\"password\"  TEXT, " +
                    "\"email\"  TEXT " +
                    ");");
            DBC.execute("INSERT INTO \"users\" (" +
                    "\"login\", " +
                    "\"password\", " +
                    "\"email\") VALUES (" +
                    "'sdfg', " +
                    "'sevg', " +
                    "'srdbg');");
            DBC.executeUpdate("UPDATE \"users\" SET " +
                    "\"login\"='abc', " +
                    "\"password\"='def' " +
                    "WHERE " +
                    "\"email\"='srdbg';");
            List list = DBC.executeQuery("SELECT * FROM \"users\";");
            for (Object o : list)
            {
                Map map = (Map) o;
                System.out.println("login = " + map.get("login"));
                System.out.println("password = " + map.get("password"));
                System.out.println("email = " + map.get("email"));
                System.out.println();
            }

        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
