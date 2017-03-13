package com.example.pellesam.outerspacemanager;

import java.util.ArrayList;

/**
 * Created by mac14 on 13/03/2017.
 */

public class Users {

    private ArrayList<User> users;

    public Users(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }

    public Integer getSize(){
        return this.users.size();
    }
}
