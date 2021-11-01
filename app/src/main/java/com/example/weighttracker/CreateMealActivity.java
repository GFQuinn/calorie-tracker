package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CreateMealActivity extends AppCompatActivity   implements CreateFoodRecyclerViewAdapter.ItemClickListener{


    CreateFoodRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);
        ArrayList<String> foodItemNames = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.foodItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CreateFoodRecyclerViewAdapter(this, foodItemNames );
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        Button addItem = (Button) findViewById(R.id.addFoodItemButton);


    }

    public void addFoodItemtoMealOnClick(View view) {

    }
    @Override
    public void onItemClick(View view, int position) {

    }
}