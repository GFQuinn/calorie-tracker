package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddFood extends AppCompatActivity {
    RadioButton gramsRadioButton;
    RadioButton mlRadioButton;
    EditText nameEditText;
    EditText brandEditText;
    EditText kiloJouleEditNumber;
    EditText carbsEditNumber;
    EditText proteinEditNumber;
    EditText fatEditNumber;
    EditText servingSizeEditNumber;
    RadioGroup unitsRadioGroup;
    DataBaseHelper dbHelper;

    //data entered by user is per 100g and needs to be converted to per 1 gram values
    int convertToGramFactor = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        gramsRadioButton = (RadioButton) findViewById(R.id.gramsRadioButton);
        mlRadioButton = (RadioButton) findViewById(R.id.mlRadioButton);
        nameEditText = (EditText) findViewById(R.id.nameText);
        brandEditText = (EditText) findViewById(R.id.brandText);
        kiloJouleEditNumber = (EditText) findViewById(R.id.kjEditText);
        carbsEditNumber = (EditText) findViewById(R.id.carbsEditText);
        proteinEditNumber = (EditText) findViewById(R.id.proteinEditText);
        fatEditNumber = (EditText) findViewById(R.id.fatEditText);
        servingSizeEditNumber = (EditText) findViewById(R.id.servingSizeEditNumber);
        unitsRadioGroup = (RadioGroup) findViewById(R.id.unitsRadioGroup);
        dbHelper = DataBaseHelper.getInstance(this);

    }


    public void gramsRadioOnCLick(View view) {
        gramsRadioButton.setChecked(true);
        mlRadioButton.setChecked(false);
    }

    public void millilitesRadioOnClick(View view) {
        mlRadioButton.setChecked(true);
        gramsRadioButton.setChecked(false);
    }

    public void addFoodItemOnClick(View view) {
        String foodItemName = nameEditText.getText().toString().trim();
        String brandName = brandEditText.getText().toString().trim();
        //get per 100 gram/milliLitre value from editTexts and convert to per grams value for database entry
        String kiloJoulePerGramValueString = kiloJouleEditNumber.getText().toString();
        String carbsPerGramValueString = carbsEditNumber.getText().toString();
        String proteinPerGramValueString = proteinEditNumber.getText().toString();
        String fatPerGramValueString = fatEditNumber.getText().toString();
        String servingSizeString = servingSizeEditNumber.getText().toString();

       if(helper.isAnyStringNull(foodItemName, brandName, kiloJoulePerGramValueString, carbsPerGramValueString, proteinPerGramValueString, fatPerGramValueString, servingSizeString))
        {
            showErrorMessage();
       }
        else
        {
            float kiloJoulePerGramValue = Float.valueOf(kiloJoulePerGramValueString) / convertToGramFactor;
            float carbsPerGramValue = Float.valueOf(carbsPerGramValueString) / convertToGramFactor;
            float proteinPerGramValue = Float.valueOf(proteinPerGramValueString) / convertToGramFactor;
            float fatPerGramValue = Float.valueOf(fatPerGramValueString) / convertToGramFactor;
            float servingSize = Float.valueOf(servingSizeString) / convertToGramFactor;
            addFoodItemToDatabase(foodItemName, brandName, kiloJoulePerGramValue,
                    carbsPerGramValue, proteinPerGramValue, fatPerGramValue, servingSize);
        }


    }

    private void addFoodItemToDatabase(String foodItemName, String brandName, float kiloJoulePerGramValue, float carbsPerGramValue,
                                       float proteinPerGramValue, float fatPerGramValue, float servingSize) {
        //sets the units value depending on selected radio button
        int checkedRadioButtonId = unitsRadioGroup.getCheckedRadioButtonId();
        String unitsString = "";

        switch (checkedRadioButtonId) {
            case R.id.gramsRadioButton:
                unitsString = "grams";
                break;
            case R.id.mlRadioButton:
                unitsString = "millilitres";
                break;
        }
        dbHelper.insertFoodItem(foodItemName, brandName, unitsString, kiloJoulePerGramValue, carbsPerGramValue,
                proteinPerGramValue, fatPerGramValue, servingSize);


        nameEditText.getText().clear();
        brandEditText.getText().clear();
        gramsRadioButton.setChecked(true);
        mlRadioButton.setChecked(false);
        kiloJouleEditNumber.getText().clear();
        carbsEditNumber.getText().clear();
        proteinEditNumber.getText().clear();
        fatEditNumber.getText().clear();
        servingSizeEditNumber.getText().clear();

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Success");
        alertDialog.setMessage(brandName + " " + foodItemName + " has been added to the database");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

    private void showErrorMessage()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage("Please fill in all fields");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }


}