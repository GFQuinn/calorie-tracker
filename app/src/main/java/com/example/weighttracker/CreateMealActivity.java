package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateMealActivity extends AppCompatActivity   implements FoodTypeRecyclerViewAdapter.ItemClickListener{


    RecipeRecyclerViewAdapter adapter;
    Recipe recipe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);
        recipe = new Recipe();

        RecyclerView recyclerView = findViewById(R.id.AddfoodItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeRecyclerViewAdapter(this, recipe);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        Button addItem = findViewById(R.id.addFoodItemButton);
    }




    public void addItemPopupOnclick(View view) {
    startActivity(new Intent(CreateMealActivity.this,AddItemPopup.class));
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
