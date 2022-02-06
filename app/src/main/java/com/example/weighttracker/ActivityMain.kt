package com.example.weighttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View

class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun weightOnClick(view: View?) {
        val intent = Intent(this, ActivityWeightTracker::class.java)
        startActivity(intent)
    }

    fun calorieOnClick(view: View?) {
        val intent = Intent(this, ActivityCalorieTrackerMenu::class.java)
        startActivity(intent)
    }

    fun graphsOnClick(view: View?) {
        val intent = Intent(this, ActivityGraph::class.java)
        startActivity(intent)
    }

    fun userOnClick(view: View?) {
        val intent = Intent(this, ActivityUserSettings::class.java)
        startActivity(intent)
    }
}