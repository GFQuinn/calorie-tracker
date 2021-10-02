package com.example.weighttracker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeHelper {


    public String getCurrentDateString(){
       return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    public String getCurrentTimeString(){
        return new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
    }

}
