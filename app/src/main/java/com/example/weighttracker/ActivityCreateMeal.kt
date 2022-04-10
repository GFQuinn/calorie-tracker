package com.example.weighttracker


import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts


class ActivityCreateMeal : AppCompatActivity(), FoodTypeRecyclerViewAdapter.ItemClickListener {
    private var adapterRecipeRecycler: ViewAdapterRecipeRecycler? = null
    private var recipe: ClassRecipe? = null
    private var totalAmountText: TextView? = null
    private var totalEnergyText: TextView? = null
    private val kiloJoules: String = "Kj"
    private var recipeNameEditText: EditText? = null
    private var recordAsMealCheckBox: CheckBox? = null
    private var saveAsAQuickMeal: CheckBox? = null
    private lateinit var dataBase: HelperDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBase = HelperDataBase.getInstance(this.applicationContext)
        setContentView(R.layout.activity_create_meal)
        recipe = ClassRecipe()
        val recyclerView = findViewById<RecyclerView>(R.id.AddfoodItemList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapterRecipeRecycler =
            ViewAdapterRecipeRecycler(this, recipe)
        adapterRecipeRecycler!!.setClickListener(this)
        recyclerView.adapter = adapterRecipeRecycler
        val addItem = findViewById<Button>(R.id.addFoodItemButton)
        recipeNameEditText = findViewById(R.id.recipeNameEditText)

        totalAmountText = findViewById(R.id.totalAmountTextField)
        totalEnergyText = findViewById(R.id.totalEnergyTextField)

        recordAsMealCheckBox = findViewById(R.id.record_checkbox)
        saveAsAQuickMeal = findViewById(R.id.save_checkbox)

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

    fun saveMealOnclick(view: View) {
        if(recipeNameEditText?.text.toString() == "")
        {
            Toast.makeText(this, "You must name the recipe", Toast.LENGTH_SHORT).show()
            return
        }

        if(recordAsMealCheckBox?.isChecked() == false && saveAsAQuickMeal?.isChecked() == false)
        {
            Toast.makeText(this, "Please check an option or both", Toast.LENGTH_SHORT).show()
            return
        }


        var recipe = adapterRecipeRecycler?.recipe
        if(recipe == null)
        {
            Toast.makeText(this, "You must add items to the recipe", Toast.LENGTH_SHORT).show()
            return
        }

        var recipeName = recipeNameEditText?.text.toString()

        if(recordAsMealCheckBox?.isChecked() == true)
        {
            dataBase.insertMealEntry(recipe)
        }
        if(saveAsAQuickMeal?.isChecked() == true)
        {
           dataBase.insertSavedRecipe(recipe, recipeName)
        }
    }
}