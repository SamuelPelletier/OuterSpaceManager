package com.example.pellesam.outerspacemanager.Entity;

/**
 * Created by mac14 on 13/03/2017.
 */

public class Search {

    private Integer amountOfEffectByLevel;
    private Integer amountOfEffectLevel0;
    private boolean building;
    private String effect;
    private Integer gasCostByLevel;
    private Integer gasCostLevel0;
    private Integer level;
    private Integer mineralCostByLevel;
    private Integer mineralCostLevel0;
    private String name;
    private Integer timeToBuildByLevel;
    private Integer timeToBuildLevel0;

    public Search(Integer amountOfEffectByLevel, Integer amountOfEffectLevel0, boolean building, String effect, Integer gasCostByLevel, Integer gasCostLevel0, Integer level, Integer mineralCostByLevel, Integer mineralCostLevel0, String name, Integer timeToBuildByLevel, Integer timeToBuildLevel0) {
        this.amountOfEffectByLevel = amountOfEffectByLevel;
        this.amountOfEffectLevel0 = amountOfEffectLevel0;
        this.building = building;
        this.effect = effect;
        this.gasCostByLevel = gasCostByLevel;
        this.gasCostLevel0 = gasCostLevel0;
        this.level = level;
        this.mineralCostByLevel = mineralCostByLevel;
        this.mineralCostLevel0 = mineralCostLevel0;
        this.name = name;
        this.timeToBuildByLevel = timeToBuildByLevel;
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }

    public Integer getGasCost(){
        Integer level = (this.level != null) ? this.level : 1;
        Integer result = this.getGasCostLevel0() + this.getGasCostByLevel() * level;
        return result;
    }

    public Integer getMineralCost(){
        Integer level = (this.level != null) ? this.level : 1;
        Integer result = this.getMineralCostLevel0() + this.getMineralCostByLevel() * level;
        return result;
    }

    public Integer getTimeToBuild(){
        Integer level = (this.level != null) ? this.level : 1;
        Integer result = this.timeToBuildLevel0 + this.timeToBuildByLevel * level;
        return result;
    }

    public Integer getAmountEffect(){
        Integer level = (this.level != null) ? this.level : 1;
        Integer result = this.amountOfEffectLevel0 + this.amountOfEffectByLevel * level;
        return result;
    }

    public Integer getAmountOfEffectByLevel() {
        return amountOfEffectByLevel;
    }

    public Integer getAmountOfEffectLevel0() {
        return amountOfEffectLevel0;
    }

    public boolean isBuilding() {
        return building;
    }

    public String getEffect() {
        return effect;
    }

    public Integer getGasCostByLevel() {
        return gasCostByLevel;
    }

    public Integer getGasCostLevel0() {
        return gasCostLevel0;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getMineralCostByLevel() {
        return mineralCostByLevel;
    }

    public Integer getMineralCostLevel0() {
        return mineralCostLevel0;
    }

    public String getName() {
        return name;
    }

    public Integer getTimeToBuildByLevel() {
        return timeToBuildByLevel;
    }

    public Integer getTimeToBuildLevel0() {
        return timeToBuildLevel0;
    }
}
