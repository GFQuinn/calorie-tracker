package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void weightOnClick(View view) {

        Intent intent = new Intent(this, WeightTracker.class);
        startActivity(intent);
    }

    public void calorieOnClick(View view) {

        Intent intent = new Intent(this, CalorieTrackerMenu.class);
        startActivity(intent);
    }

    public void graphsOnClick(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }


}