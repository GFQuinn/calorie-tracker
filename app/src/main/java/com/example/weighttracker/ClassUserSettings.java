package com.example.weighttracker;

public class ClassUserSettings {

    private String username;
    private boolean usingCalories;
    private float weightGoal;
    private int monEnergyGoal;
    private int tuesEnergyGoal;
    private int wedEnergyGoal;
    private int thuEnergyGoal;
    private int friEnergyGoal;
    private int satEnergyGoal;
    private int sunEnergyGoal;
    private int carbRatio;
    private int fatRatio;
    private int proteinRatio;


    ClassUserSettings(String username, boolean usingCalories, float weightGoal, int monEnergyGoal, int tuesEnergyGoal,
                      int wedEnergyGoal, int thuEnergyGoal, int friEnergyGoal, int satEnergyGoal, int sunEnergyGoal,
                      int carbRatio, int fatRatio, int proteinRatio)
    {
        this.username = username;
        this.usingCalories = usingCalories;
        this.weightGoal = weightGoal;
        this.monEnergyGoal = monEnergyGoal;
        this.tuesEnergyGoal = tuesEnergyGoal;
        this.wedEnergyGoal = wedEnergyGoal;
        this.thuEnergyGoal = thuEnergyGoal;
        this.friEnergyGoal = friEnergyGoal;
        this.satEnergyGoal = satEnergyGoal;
        this.sunEnergyGoal = sunEnergyGoal;
        this.carbRatio = carbRatio;
        this.fatRatio = fatRatio;
        this.proteinRatio = proteinRatio;
    }


    public String getUsername() { return username; }

    public boolean getUsingCalories() { return usingCalories;}

    public float getWeightGoal() { return weightGoal; }

    public int[] getWeekEnergyGoals()
    {
        return new int[] { monEnergyGoal, tuesEnergyGoal, wedEnergyGoal, thuEnergyGoal, friEnergyGoal, satEnergyGoal, sunEnergyGoal};
    }

    public int[] getMacroRatios()
    {
        return new int[] { carbRatio, fatRatio, proteinRatio };
    }

 }
