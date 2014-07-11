package com.luckyfenix.entity;

import java.sql.Date;

/**
 * Created by LuckyFenix on 08.07.2014. ${TEMP}
 */
public class User
{
    private int id;
    private String login;
    private String password;
    private String email;
    private Date regDate;
    private boolean is_admin;

    public User(int id, String login, String password, String email, Date regDate, boolean is_admin)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.regDate = regDate;
        this.is_admin = is_admin;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean isAdmin()
    {
        return is_admin;
    }

    public void setIsAdmin(boolean is_admin)
    {
        this.is_admin = is_admin;
    }

    public Date getRegDate()
    {
        return regDate;
    }

    public void setRegDate(Date regDate)
    {
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object user)
    {
        User myUser = (User) user;
        return this.login.equals(myUser.login) &&
                this.password.equals(myUser.password) &&
                this.email.equals(myUser.email);
    }
}

