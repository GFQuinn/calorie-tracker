package com.example.weighttracker;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WeightTracker extends AppCompatActivity {

    private DataBaseHelper dbHelper;
    private DateTimeHelper dateTimeHelper;
    private EditText weightEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weighttracker);
        dbHelper = DataBaseHelper.getInstance(this);
        dateTimeHelper = new DateTimeHelper();

        weightEdit = (EditText) findViewById(R.id.weightInput);


    }

    public void acceptWeightonCLick(View view) {

        String currentDate = dateTimeHelper.getCurrentDateString();
        String currentTime = dateTimeHelper.getCurrentTimeString();
        String weightString = weightEdit.getText().toString();
        float weightValue = Float.parseFloat(weightString);

        dbHelper.insertWeightEntry(weightValue, currentTime, currentDate);
       weightEdit.getText().clear();


    }
}

