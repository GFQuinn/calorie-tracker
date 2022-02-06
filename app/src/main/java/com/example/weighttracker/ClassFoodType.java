package com.example.weighttracker;

import android.os.Parcel;
import android.os.Parcelable;

public class ClassFoodType implements Parcelable {
    private final String brandName;
    private final String foodName;
    private final String units;
    private final int calories;
    private final float carbs;

    private final float sugars;
    private final float fat;
    private final float protein;
    private final int servingSize;
    private final String servingLabel;

    ClassFoodType(String foodName,
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

    protected ClassFoodType(Parcel in) {
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

    public static final Creator<ClassFoodType> CREATOR = new Creator<ClassFoodType>() {
        @Override
        public ClassFoodType createFromParcel(Parcel in) {
            return new ClassFoodType(in);
        }

        @Override
        public ClassFoodType[] newArray(int size) {
            return new ClassFoodType[size];
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

    public int getServingSize() { return servingSize;}

    public String getServingLabel() { return servingLabel;}
}

