package com.example.payucart.model.levelIncome;

public class LevelIncomeModel {
    private String levelName;
    private String levelActive;
    private String levelInActive;
    private String levelRate;
    private String levelDailIncome;


    public LevelIncomeModel(String levelName, String levelActive, String levelInActive, String levelRate, String levelDailIncome) {
        this.levelName = levelName;
        this.levelActive = levelActive;
        this.levelInActive = levelInActive;
        this.levelRate = levelRate;
        this.levelDailIncome = levelDailIncome;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelActive() {
        return levelActive;
    }

    public void setLevelActive(String levelActive) {
        this.levelActive = levelActive;
    }

    public String getLevelInActive() {
        return levelInActive;
    }

    public void setLevelInActive(String levelInActive) {
        this.levelInActive = levelInActive;
    }

    public String getLevelRate() {
        return levelRate;
    }

    public void setLevelRate(String levelRate) {
        this.levelRate = levelRate;
    }

    public String getLevelDailIncome() {
        return levelDailIncome;
    }

    public void setLevelDailIncome(String levelDailIncome) {
        this.levelDailIncome = levelDailIncome;
    }
}
