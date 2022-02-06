package com.example.weighttracker;

import java.util.ArrayList;

public class ClassFoodTypes {

    ArrayList<ClassFoodType> foodTypes;


    public ClassFoodTypes()
    {
        foodTypes = new ArrayList<>();
    }

    public void add(ClassFoodType foodType)
    {
        foodTypes.add(foodType);
    }

    public String[] getNameAndBrand(int index)
    {
        ClassFoodType typeAtindex = foodTypes.get(index);
        return typeAtindex.getNameAndBrand();
    }

    public int size()
    {
        return foodTypes.size();
    }

    public ClassFoodType get(int index)
    {
        return foodTypes.get(index);
    }

    public String getUnitsAtIndex(int index)
    {
        return foodTypes.get(index).getUnits();
    }

    public int getServingSizeAtIndex(int index) {
        return foodTypes.get(index).getServingSize();
    }

    public String getServingLabelAtIndex(int index) {
        return foodTypes.get(index).getServingLabel();
    }
}
