package com.example.pellesam.outerspacemanager;

import java.util.ArrayList;

/**
 * Created by mac14 on 07/03/2017.
 */

public class Buildings {

    private ArrayList<Building> buildings;

    public Buildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<Building> getBuildings(){
        return this.buildings;
    }

    public Integer getSize(){
        return this.buildings.size();
    }
}
