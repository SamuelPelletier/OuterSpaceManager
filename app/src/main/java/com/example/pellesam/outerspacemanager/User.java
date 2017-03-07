package com.example.pellesam.outerspacemanager;

/**
 * Created by pellesam on 06/03/2017.
 */

public class User {
    private String username;
    private String password;
    private String token;
    private String point;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getToken(){
        return this.token;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPoint(){
        return this.point;
    }
}
