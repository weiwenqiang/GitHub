package com.chen.test.Entity;

/**
 * Created by long on 17-3-22.
 */
public class DataBean {
    /**
     * user : d
     * pass : value
     * id : 1
     */

    private String user;
    private String pass;
    private int id;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\"user\":'" + user + '\'' +
                ", \"pass\":'" + pass + '\'' +
                ", \"id\":" + id +
                '}';
    }
}
