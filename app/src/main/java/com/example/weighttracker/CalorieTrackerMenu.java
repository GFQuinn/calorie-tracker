package com.example.weighttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CalorieTrackerMenu extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorietrackermenu);
    }

    public void addFoodButtonOnclick(View view) {
        Intent intent = new Intent(this, AddFood.class);
        startActivity(intent);
    }

    public void createMealButtonOnclick(View view) {
    }

    public void quickAddButtonOnclick(View view) {
    }
}
