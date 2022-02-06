package com.example.weighttracker;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityWeightTracker extends AppCompatActivity {

    private HelperDataBase dbHelper;
    private HelperDateTime helperDateTime;
    private EditText weightEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weighttracker);
        dbHelper = HelperDataBase.getInstance(this);
        helperDateTime = new HelperDateTime();

        weightEdit = (EditText) findViewById(R.id.weightInput);


    }

    public void acceptWeightonCLick(View view) {

        String currentDate = helperDateTime.getCurrentDateString();
        String currentTime = helperDateTime.getCurrentTimeString();
        String weightString = weightEdit.getText().toString();
        float weightValue = Float.parseFloat(weightString);

        dbHelper.insertWeightEntry(weightValue, currentTime, currentDate);
       weightEdit.getText().clear();


    }
}

