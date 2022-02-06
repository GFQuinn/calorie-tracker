package com.example.weighttracker;

import java.util.ArrayList;

public class ClassRecipe {
    private ArrayList<ClassIngredient> ingredientList;
    int totalKJ = 0;
    int totalAmount = 0;

    public ClassRecipe()
    {
        ingredientList = new ArrayList<>();
    }


    public void addIngredient(ClassIngredient ingredient)
    {
        ingredientList.add(ingredient);
        totalKJ = totalKJ + ingredient.getEnergy();
        totalAmount = totalAmount + ingredient.getAmount();
    }

    public void removeIngredient(int index)
    {
        ingredientList.remove(index);
    }


    public int size() {
        return ingredientList.size();
    }

    public ClassIngredient getIngredientDataAtIndex(int index)
    {
        return ingredientList.get(index);
    }


}
