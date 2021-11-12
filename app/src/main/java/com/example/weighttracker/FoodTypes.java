package com.example.weighttracker;

import java.util.ArrayList;

public class FoodTypes {

    ArrayList<FoodType> foodTypes;


    public FoodTypes()
    {
        foodTypes = new ArrayList<>();
    }

    public void add(FoodType foodType)
    {
        foodTypes.add(foodType);
    }

    public ArrayList<String[]> getNamesAndBrands()
    {
        ArrayList<String[]> namesAndBrands = new ArrayList<>();

        for (FoodType f: foodTypes) {
            String[] nameAndBrand = {f.getFoodName(), f.getBrandName()};
            namesAndBrands.add(nameAndBrand);
        }
        return namesAndBrands;
    }

    public String[] getNameAndBrand(int index)
    {
        FoodType typeAtindex = foodTypes.get(index);
        return typeAtindex.getNameAndBrand();
    }

    public int size()
    {
        return foodTypes.size();
    }

    public FoodType get(int index)
    {
        return foodTypes.get(index);
    }

    public String getUnitsAtIndex(int index)
    {
        return foodTypes.get(index).getUnits();
    }

}
