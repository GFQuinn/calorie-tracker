package com.example.weighttracker;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodType implements Parcelable {
    private String brandName;
    private String foodName;
    private String units;
    private int calories;
    private float carbs;
    private float sugars;
    private float fat;
    private float protein;
    private int servingSize;
    private String servingLabel;

    FoodType(String foodName,
            String brandName,
            String units,
            int calories,
            float carbs,
            float sugars,
             float protein,
            float fat,
             int servingSize,
             String servingLabel
 ) {
        this.brandName = brandName;
        this.foodName = foodName;
        this.units = units;
        this.calories = calories;
        this.carbs = carbs;
        this.sugars = sugars;
        this.fat = fat;
        this.protein = protein;
        this.servingSize = servingSize;
        this.servingLabel = servingLabel;
    }

    protected FoodType(Parcel in) {
        brandName = in.readString();
        foodName = in.readString();
        units = in.readString();
        calories = in.readInt();
        carbs = in.readFloat();
        sugars = in.readFloat();
        fat = in.readFloat();
        protein = in.readFloat();
        servingSize = in.readInt();
        servingLabel = in.readString();
    }

    public static final Creator<FoodType> CREATOR = new Creator<FoodType>() {
        @Override
        public FoodType createFromParcel(Parcel in) {
            return new FoodType(in);
        }

        @Override
        public FoodType[] newArray(int size) {
            return new FoodType[size];
        }
    };

    public String getBrandName() {
        return brandName;
    }

    public String getFoodName() {
        return foodName;
    }

    public String[] getNameAndBrand()
    {
        return new String[]{foodName, brandName};
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brandName);
        dest.writeString(foodName);
        dest.writeString(units);
        dest.writeInt(calories);
        dest.writeFloat(carbs);
        dest.writeFloat(sugars);
        dest.writeFloat(fat);
        dest.writeFloat(protein);
        dest.writeInt(servingSize);
        dest.writeString(servingLabel);
    }

    public String getUnits() {
        return units;
    }

    public int getCalories()
    {
        return calories;
    }
}

