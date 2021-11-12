package com.example.weighttracker;

import java.util.ArrayList;

public class Recipe {
    private ArrayList<Ingredient> ingredientList;

    public void addIngredient(Ingredient ingredient)
    {
        ingredientList.add(ingredient);
    }

    public void removeIngredient(int index)
    {
        ingredientList.remove(index);
    }


    public int size() {
        return ingredientList.size();
    }

    public String[] getIngredientDataAtIndex(int index)
    {
        return new String[]{ ingredientList.get(index).getFoodName()
        , String.valueOf(ingredientList.get(index).getAmount())
        , String.valueOf(ingredientList.get(index).getEnergy())};
    }

}
