package com.example.pellesam.outerspacemanager;

/**
 * Created by mac14 on 07/03/2017.
 */

public class Ship {

    private Integer gasCost;
    private Integer life;
    private Integer maxAttack;
    private Integer minAttack;
    private Integer mineralCost;
    private String name;
    private Integer shield;
    private Integer spatioportLevelNeeded;
    private Integer speed;
    private Integer timeToBuild;
    private Integer amount;


    public Ship(Integer gasCost, Integer life, Integer maxAttack, Integer minAttack, Integer mineralCost, String name, Integer shield, Integer spatioportLevelNeeded, Integer speed, Integer timeToBuild, Integer amount) {
        this.gasCost = gasCost;
        this.life = life;
        this.maxAttack = maxAttack;
        this.minAttack = minAttack;
        this.mineralCost = mineralCost;
        this.name = name;
        this.shield = shield;
        this.spatioportLevelNeeded = spatioportLevelNeeded;
        this.speed = speed;
        this.timeToBuild = timeToBuild;
        this.amount = amount;
    }

    public Ship(Integer amount){
        this.amount = amount;
    }

    public Integer getGasCost() {
        return gasCost;
    }

    public Integer getLife() {
        return life;
    }

    public Integer isMaxAttack() {
        return maxAttack;
    }

    public Integer getMinAttack() {
        return minAttack;
    }

    public Integer getMineralCost() {
        return mineralCost;
    }

    public String getName() {
        return name;
    }

    public Integer getShield() {
        return shield;
    }

    public Integer getSpatioportLevelNeeded() {
        return spatioportLevelNeeded;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getTimeToBuild() {
        return timeToBuild;
    }

    public Integer getAmount() {
        return amount;
    }
}
