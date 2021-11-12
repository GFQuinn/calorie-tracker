package com.example.weighttracker;

public class Ingredient  {

    private int amount;
    private FoodType foodType;

    Ingredient(int amount, FoodType type)
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

    public int getEnergy()
    {
        return amount * foodType.getCalories();
    }

}
