package com.example.pellesam.outerspacemanager.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mac14 on 20/03/2017.
 */

public class FleetBattle implements Serializable{

    private Integer capacity;

    private ArrayList<Ship> fleet;

    private Integer survivingShips;

    public FleetBattle(Integer capacity, Integer survivingShips) {
        this.capacity = capacity;
        this.survivingShips = survivingShips;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public ArrayList<Ship> getFleet() {
        return fleet;
    }

    public Integer getSurvivingShips() {
        return survivingShips;
    }
}
