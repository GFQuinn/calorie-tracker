package com.example.weighttracker;

public class ClassIngredient {

    private int amount;
    private ClassFoodType foodType;

    ClassIngredient(int amount, ClassFoodType type)
    {
        foodType = type;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getFoodName()
    {
        return foodType.getFoodName();
    }

    public String getUnits(){return foodType.getUnits();}

    public int getEnergy()
    {
        return amount * foodType.getCalories();
    }

    public String getBrandName() {
        return foodType.getBrandName();
    }
}
