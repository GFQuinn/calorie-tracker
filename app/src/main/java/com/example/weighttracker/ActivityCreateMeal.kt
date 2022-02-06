package com.example.weighttracker


import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts


class ActivityCreateMeal : AppCompatActivity(), FoodTypeRecyclerViewAdapter.ItemClickListener {
    private var adapterRecipeRecycler: ViewAdapterRecipeRecycler? = null
    private var recipe: ClassRecipe? = null
    private var totalAmountText: TextView? = null
    private var totalEnergyText: TextView? = null
    private val kiloJoules: String = "Kj"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meal)
        recipe = ClassRecipe()
        val recyclerView = findViewById<RecyclerView>(R.id.AddfoodItemList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapterRecipeRecycler =
            ViewAdapterRecipeRecycler(this, recipe)
        adapterRecipeRecycler!!.setClickListener(this)
        recyclerView.adapter = adapterRecipeRecycler
        val addItem = findViewById<Button>(R.id.addFoodItemButton)

        totalAmountText = findViewById(R.id.totalAmountTextField)
        totalEnergyText = findViewById(R.id.totalEnergyTextField)
    }

    fun addItemPopupOnclick(view: View) {
        openSomeActivityForResult()
    }

    override fun onItemClick(view: View, position: Int) {}

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data

            val amount = data!!.getIntExtra("amount", 0)
            val foodType: ClassFoodType? = data!!.getParcelableExtra("foodItem")
            val ingredient: ClassIngredient =
                ClassIngredient(amount, foodType)
            val recipeSize  = recipe?.size()
            recipe?.addIngredient(ingredient)
            if (recipeSize != null) {
                adapterRecipeRecycler?.notifyItemInserted(recipeSize)
            };
            totalEnergyText?.text  = recipe?.totalKJ.toString() + kiloJoules


            totalAmountText?.text  = recipe?.totalAmount.toString()


        }
    }

    private fun openSomeActivityForResult() {
        val intent = Intent(this, ActivityAddItemPopup::class.java)
        resultLauncher.launch(intent)
    }

    fun saveMealOnclick(view: View) {}
}