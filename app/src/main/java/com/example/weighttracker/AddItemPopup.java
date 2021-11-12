package com.example.weighttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddItemPopup  extends Activity  implements FoodTypeRecyclerViewAdapter.ItemClickListener{

    FoodTypes foodItems;
    DataBaseHelper dataBaseHelper;
    FoodTypeRecyclerViewAdapter adapter;
    int selectedItem = -1;
    EditText foodItemWeightEditText;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.add_item_popup_window);

        dataBaseHelper = DataBaseHelper.getInstance(this);
        foodItems = dataBaseHelper.getAllFoodItems();


        RecyclerView recyclerView = findViewById(R.id.foodItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodTypeRecyclerViewAdapter(this, foodItems );
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        foodItemWeightEditText = findViewById(R.id.foodItemWeightEditText);

    }


    @Override
    public void onItemClick(View view, int position) {
        String units = foodItems.getUnitsAtIndex(position);
        foodItemWeightEditText.setHint(units);
        selectedItem = position;
    }

    public void addItemToMealOnClick(View view) {
        Intent intent = new Intent(AddItemPopup.this, CreateMealActivity.class);
        if(!TextUtils.isEmpty(foodItemWeightEditText.getText()))
        {
            int amount =  Integer.parseInt(String.valueOf(foodItemWeightEditText.getText()));
            if(selectedItem != -1 && amount != 0 )
            {
                intent.putExtra("foodItem", foodItems.get(selectedItem));
                intent.putExtra("amount", amount);
            }

        }
        startActivity(intent);
    }



}
