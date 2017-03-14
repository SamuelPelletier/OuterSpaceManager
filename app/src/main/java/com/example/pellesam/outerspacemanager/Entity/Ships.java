package com.example.pellesam.outerspacemanager.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mac14 on 07/03/2017.
 */

public class Ships implements Serializable{

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
    
    public Integer getNumberOfShip(){
        Integer numberShip = 0;
        for (Integer i = 0; i<this.getSize(); i++){
            if(this.ships.get(i).getAmount() > 0){
                numberShip += this.ships.get(i).getAmount();
            }
        }
        return numberShip;
    }
}
