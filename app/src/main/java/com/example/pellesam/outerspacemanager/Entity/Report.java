package com.example.pellesam.outerspacemanager.Entity;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 * Created by mac14 on 20/03/2017.
 */

public class Report {

    private ArrayList<Ship> attackerFleet;

    private FleetBattle attackerFleetAfterBattle;

    private long date;

    private long dateInv;

    private ArrayList<Ship> defenderFleet;

    private FleetBattle defenderFleetAfterBattle;

    private String from;

    private double gasWon;

    private double mineralsWon;

    private String to;

    private String type;

    public Report(ArrayList<Ship> attackerFleet, FleetBattle attackerFleetAfterBattle, long date, long dateInv, ArrayList<Ship> defenderFleet, FleetBattle defenderFleetAfterBattle, String from, double gasWon, double mineralsWon, String to, String type) {
        this.attackerFleet = attackerFleet;
        this.attackerFleetAfterBattle = attackerFleetAfterBattle;
        this.date = date;
        this.dateInv = dateInv;
        this.defenderFleet = defenderFleet;
        this.defenderFleetAfterBattle = defenderFleetAfterBattle;
        this.from = from;
        this.gasWon = gasWon;
        this.mineralsWon = mineralsWon;
        this.to = to;
        this.type = type;
    }

    public ArrayList<Ship> getAttackerFleet() {
        return attackerFleet;
    }

    public FleetBattle getAttackerFleetAfterBattle() {
        return attackerFleetAfterBattle;
    }

    public long getDate() {
        return date;
    }

    public long getDateInv() {
        return dateInv;
    }

    public ArrayList<Ship> getDefenderFleet() {
        return defenderFleet;
    }

    public FleetBattle getDefenderFleetAfterBattle() {
        return defenderFleetAfterBattle;
    }

    public String getFrom() {
        return from;
    }

    public double getGasWon() {
        return gasWon;
    }

    public double getMineralsWon() {
        return mineralsWon;
    }

    public String getTo() {
        return to;
    }

    public String getType() {
        return type;
    }
}
