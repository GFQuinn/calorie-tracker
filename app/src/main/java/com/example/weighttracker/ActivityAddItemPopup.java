package com.example.weighttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityAddItemPopup extends Activity  implements FoodTypeRecyclerViewAdapter.ItemClickListener{

    private ClassFoodTypes foodItems;
    private HelperDataBase helperDataBase;
    private FoodTypeRecyclerViewAdapter adapter;
    private int selectedItem = -1;

    private  EditText foodItemWeightEditText;
    final private String add = "add ";
    private Button servingSizeButton;
    private TextView servingSizeTextView;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.add_item_popup_window);

        helperDataBase = HelperDataBase.getInstance(this);
        foodItems = helperDataBase.getAllFoodItems();


        RecyclerView recyclerView = findViewById(R.id.foodItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodTypeRecyclerViewAdapter(this, foodItems );
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        foodItemWeightEditText = findViewById(R.id.foodItemWeightEditText);
        servingSizeTextView = findViewById(R.id.servingSizeText);
        servingSizeButton = findViewById(R.id.addServingButton);
    }

    @Override
    public void onItemClick(View view, int position) {

        String units = foodItems.getUnitsAtIndex(position);
        String servingSize = String.valueOf(foodItems.getServingSizeAtIndex(position));
        String servingLabel = String.valueOf(foodItems.getServingLabelAtIndex(position));

        servingSizeTextView.setText(servingSize);
        servingSizeButton.setText((add + servingLabel));

        foodItemWeightEditText.setHint(units);
        selectedItem = position;
    }

    public void addItemToMealOnClick(View view) {
        Intent intent = new Intent(ActivityAddItemPopup.this, ActivityCreateMeal.class);
        if(!TextUtils.isEmpty(foodItemWeightEditText.getText()))
        {
            int amount =  Integer.parseInt(String.valueOf(foodItemWeightEditText.getText()));
            if(selectedItem != -1 && amount != 0 )
            {

                intent.putExtra("foodItem", foodItems.get(selectedItem));
                intent.putExtra("amount", amount);
                setResult(Activity.RESULT_OK, intent);
            }
            else
            {
                setResult(Activity.RESULT_CANCELED);
            }

        }
        else
        {
            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }


    public void addServingOnclick(View view) {

        if(!TextUtils.isEmpty(servingSizeTextView.getText().toString().trim()) && selectedItem != -1)
        {
            int currentAmount = 0;
            if(!TextUtils.isEmpty(foodItemWeightEditText.getText().toString().trim()))
            {
                currentAmount = Integer.parseInt(foodItemWeightEditText.getText().toString());
            }
            currentAmount = currentAmount + foodItems.getServingSizeAtIndex(selectedItem);
            foodItemWeightEditText.setText(String.valueOf(currentAmount));
        }
    }
}
