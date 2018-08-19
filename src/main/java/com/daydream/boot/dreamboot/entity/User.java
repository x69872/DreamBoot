package com.daydream.boot.dreamboot.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Long objectid;
    private String username;
    private String password;
    private String email;
    private Long telephone;
    private String sex;
    private Date creationtime;
    private String createdby;
    private Date lastupdatedtime;
    private String lastupdatedby;

    public User(Long objectid, String username, String password, String email, Long telephone, String sex, Date creationtime, String createdby, Date lastupdatedtime, String lastupdatedby)
    {
        this.objectid = objectid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.sex = sex;
        this.creationtime = creationtime;
        this.createdby = createdby;
        this.lastupdatedtime = lastupdatedtime;
        this.lastupdatedby = lastupdatedby;
    }

    public User()
    {
        super();
    }

    public Long getObjectid()
    {
        return objectid;
    }

    public void setObjectid(Long objectid)
    {
        this.objectid = objectid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email == null ? null : email.trim();
    }

    public Long getTelephone()
    {
        return telephone;
    }

    public void setTelephone(Long telephone)
    {
        this.telephone = telephone;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getCreationtime()
    {
        return creationtime;
    }

    public void setCreationtime(Date creationtime)
    {
        this.creationtime = creationtime;
    }

    public String getCreatedby()
    {
        return createdby;
    }

    public void setCreatedby(String createdby)
    {
        this.createdby = createdby == null ? null : createdby.trim();
    }

    public Date getLastupdatedtime()
    {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(Date lastupdatedtime)
    {
        this.lastupdatedtime = lastupdatedtime;
    }

    public String getLastupdatedby()
    {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby)
    {
        this.lastupdatedby = lastupdatedby == null ? null : lastupdatedby.trim();
    }

    @Override
    public boolean equals(Object that)
    {
        if (this == that)
        {
            return true;
        }
        if (that == null)
        {
            return false;
        }
        if (getClass() != that.getClass())
        {
            return false;
        }
        User other = (User) that;
        return (this.getObjectid() == null ? other.getObjectid() == null : this.getObjectid().equals(other.getObjectid())) && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername())) && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword())) && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail())) && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone())) && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex())) && (this.getCreationtime() == null ? other.getCreationtime() == null : this.getCreationtime().equals(other.getCreationtime())) && (this.getCreatedby() == null ? other.getCreatedby() == null : this.getCreatedby().equals(other.getCreatedby())) && (this.getLastupdatedtime() == null ? other.getLastupdatedtime() == null : this.getLastupdatedtime().equals(other.getLastupdatedtime())) && (this.getLastupdatedby() == null ? other.getLastupdatedby() == null : this.getLastupdatedby().equals(other.getLastupdatedby()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getObjectid() == null) ? 0 : getObjectid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getCreationtime() == null) ? 0 : getCreationtime().hashCode());
        result = prime * result + ((getCreatedby() == null) ? 0 : getCreatedby().hashCode());
        result = prime * result + ((getLastupdatedtime() == null) ? 0 : getLastupdatedtime().hashCode());
        result = prime * result + ((getLastupdatedby() == null) ? 0 : getLastupdatedby().hashCode());
        return result;
    }
}