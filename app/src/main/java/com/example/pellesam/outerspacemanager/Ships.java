package com.example.pellesam.outerspacemanager;

import java.util.ArrayList;

/**
 * Created by mac14 on 07/03/2017.
 */

public class Ships {

    private ArrayList<Ship> ships;

    public Ships(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public ArrayList<Ship> getShips(){
        return this.ships;
    }

    public Integer getSize(){
        return this.ships.size();
    }
}
