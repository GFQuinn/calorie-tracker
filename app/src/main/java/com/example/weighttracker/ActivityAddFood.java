package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class ActivityAddFood extends AppCompatActivity {
    RadioButton gramsRadioButton;
    RadioButton mlRadioButton;
    EditText nameEditText;
    EditText brandEditText;
    EditText kiloJouleEditNumber;
    EditText carbsEditNumber;
    EditText sugarsEditNumber;
    EditText proteinEditNumber;
    EditText fatEditNumber;
    EditText servingSizeEditNumber;
    EditText servingLabelEditText;
    RadioGroup unitsRadioGroup;
    HelperDataBase dbHelper;

    //data entered by user is per 100g and needs to be converted to per 1 gram values
    int convertToGramFactor = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        gramsRadioButton = findViewById(R.id.gramsRadioButton);
        mlRadioButton = findViewById(R.id.mlRadioButton);
        nameEditText = findViewById(R.id.nameText);
        brandEditText = findViewById(R.id.brandText);
        kiloJouleEditNumber = findViewById(R.id.kjEditText);
        carbsEditNumber = findViewById(R.id.carbsEditText);
        sugarsEditNumber = findViewById(R.id.sugarEditText);
        proteinEditNumber = findViewById(R.id.proteinEditText);
        fatEditNumber = findViewById(R.id.fatEditText);
        servingSizeEditNumber = findViewById(R.id.servingSizeEditNumber);
        servingLabelEditText = findViewById(R.id.servingLabelEditText);
        unitsRadioGroup = findViewById(R.id.unitsRadioGroup);
        dbHelper = HelperDataBase.getInstance(this);

    }


    public void gramsRadioOnCLick(View view) {
        gramsRadioButton.setChecked(true);
        mlRadioButton.setChecked(false);
    }

    public void millilitresRadioOnClick(View view) {
        mlRadioButton.setChecked(true);
        gramsRadioButton.setChecked(false);
    }

    public void addFoodItemOnClick(View view) {
        String foodItemName = nameEditText.getText().toString().trim();
        String brandName = brandEditText.getText().toString().trim();
        //get per 100 gram/milliLitre value from editTexts and convert to per grams value for database entry
        String kiloJoulePerGramValueString = kiloJouleEditNumber.getText().toString();
        String carbsPerGramValueString = carbsEditNumber.getText().toString();
        String sugarsPerGramValueString = sugarsEditNumber.getText().toString();
        String proteinPerGramValueString = proteinEditNumber.getText().toString();
        String fatPerGramValueString = fatEditNumber.getText().toString();
        String servingSizeString = servingSizeEditNumber.getText().toString();
        String servingLabelString = servingLabelEditText.getText().toString();


       if(HelperMisc.isAnyStringNull(foodItemName, brandName, kiloJoulePerGramValueString, carbsPerGramValueString, sugarsPerGramValueString, proteinPerGramValueString, fatPerGramValueString, servingSizeString))
        {
            showErrorMessage();
       }
        else
        {
            float kiloJoulePerGramValue = Float.parseFloat(kiloJoulePerGramValueString) / convertToGramFactor;
            float carbsPerGramValue = Float.parseFloat(carbsPerGramValueString) / convertToGramFactor;
            float sugarsPerGramValue =  Float.parseFloat(sugarsPerGramValueString) / convertToGramFactor;
            float proteinPerGramValue = Float.parseFloat(proteinPerGramValueString) / convertToGramFactor;
            float fatPerGramValue = Float.parseFloat(fatPerGramValueString) / convertToGramFactor;
            float servingSize = Float.parseFloat(servingSizeString);

            addFoodItemToDatabase(foodItemName, brandName, kiloJoulePerGramValue,
                    carbsPerGramValue, sugarsPerGramValue, proteinPerGramValue, fatPerGramValue, servingSize, servingLabelString);
        }


    }

    private void addFoodItemToDatabase(String foodItemName, String brandName, float kiloJoulePerGramValue, float carbsPerGramValue, float sugarsPerGramValue,
                                       float proteinPerGramValue, float fatPerGramValue, float servingSize, String servingLabelString) {
        //sets the units value depending on selected radio button
        int checkedRadioButtonId = unitsRadioGroup.getCheckedRadioButtonId();
        String unitsString;

        if(checkedRadioButtonId == R.id.gramsRadioButton)
        {
            unitsString = "grams";
        }
        else
        {
            unitsString = "millilitres";
        }


        dbHelper.insertFoodItem(foodItemName, brandName, unitsString, kiloJoulePerGramValue, carbsPerGramValue, sugarsPerGramValue,
                proteinPerGramValue, fatPerGramValue, servingSize, servingLabelString);

        nameEditText.getText().clear();
        brandEditText.getText().clear();
        gramsRadioButton.setChecked(true);
        mlRadioButton.setChecked(false);
        kiloJouleEditNumber.getText().clear();
        carbsEditNumber.getText().clear();
        sugarsEditNumber.getText().clear();
        proteinEditNumber.getText().clear();
        fatEditNumber.getText().clear();
        servingSizeEditNumber.getText().clear();
        servingLabelEditText.getText().clear();

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