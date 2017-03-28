package com.example.pellesam.outerspacemanager.Entity;

/**
 * Created by pellesam on 06/03/2017.
 */

public class User {
    private String email;
    private String username;
    private String password;
    private String token;
    private double points;
    private Double gas;
    private Integer gasModifier;
    private Double minerals;
    private Integer mineralsModifier;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getToken(){
        return this.token;
    }

    public String getEmail(){
        return this.email;
    }

    public String getUsername(){
        return this.username;
    }

    public double getPoints(){
        return this.points;
    }

    public Double getGas() {
        return gas;
    }

    public Integer getGasModifier() {
        return gasModifier;
    }

    public Double getMinerals() {
        return minerals;
    }

    public Integer getMineralsModifier() {
        return mineralsModifier;
    }
}
