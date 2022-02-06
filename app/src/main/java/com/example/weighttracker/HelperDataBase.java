package com.example.weighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HelperDataBase extends SQLiteOpenHelper {
    private static HelperDataBase sInstance;

    private static final String DEFAULT_USER = "";
    private static final String DEFAULT_USER_VALUES = "0";
    private static final String DEFAULT_ENERGY_UNIT = "Kj";


    //User table
    private static final String USERS_TABLE = "USERS_TABLE";
    private static final String USERNAME = "USERNAME";
    private static final String USER_WEIGHT_TARGET = "USER_WEIGHT_TARGET";
    private static final String DAILY_CARBS_RATIO = "DAILY_CARBS_GOAL";
    private static final String DAILY_PROTEIN_RATIO = "DAILY_PROTEIN_GOAL";
    private static final String DAILY_FAT_RATIO = "DAILY_FAT_GOAL";
    private static final String USE_KILOJOULES = "USE_KILOJOULES";
    private static final String MONDAY_ENERGY_TARGET = "MONDAY_ENERGY_TARGET";
    private static final String TUESDAY_ENERGY_TARGET = "TUESDAY_ENERGY_TARGET";
    private static final String WEDNESDAY_ENERGY_TARGET = "WEDNESDAY_ENERGY_TARGET";
    private static final String THURSDAY_ENERGY_TARGET = "THURSDAY_ENERGY_TARGET";
    private static final String FRIDAY_ENERGY_TARGET = "FRIDAY_ENERGY_TARGET";
    private static final String SATURDAY_ENERGY_TARGET = "SATURDAY_ENERGY_TARGET";
    private static final String SUNDAY_ENERGY_TARGET = "SUNDAY_ENERGY_TARGET";


    //Food type table
    private static final String FOOD_TYPE_TABLE = "FOOD_TYPE_TABLE";
    private static final String FOOD_TYPE_NAME = "FOOD_TYPE_NAME";
    private static final String FOOD_TYPE_BRAND = "FOOD_TYPE_BRAND";
    private static final String FOOD_TYPE_UNITS = "FOOD_TYPE_UNITS";
    private static final String FOOD_TYPE_KJ = "FOOD_TYPE_CALORIES";
    private static final String FOOD_TYPE_CARBS = "FOOD_TYPE_CARBS";
    private static final String FOOD_TYPE_SUGARS = "FOOD_TYPE_SUGARS";
    private static final String FOOD_TYPE_PROTEIN = "FOOD_TYPE_PROTEIN";
    private static final String FOOD_TYPE_FAT = "FOOD_TYPE_FAT";
    private static final String FOOD_TYPE_SERVING_SIZE = "FOOD_TYPE_SERVING_SIZE";
    private static final String FOOD_TYPE_SERVING_LABEL = "FOOD_TYPE_SERVING_LABEL";

    //Recipe table
    private static final String RECIPE_TABLE = "RECIPE_TABLE";
    private static final String RECIPE_NAME = "RECIPE_NAME";
    private static final String RECIPE_ID = "RECIPE_ID";

    //Saved Recipe table
    private static final String SAVED_RECIPE_TABLE = "SAVED_RECIPE_TABLE";
    private static final String SAVED_RECIPE_NAME = "SAVED_RECIPE_NAME";


    //Meal table
    private static final String MEALS_TABLE = "MEALS_TABLE";
    private static final String MEALS_DATE = "MEALS_DATE";
    private static final String MEALS_TIME = "MEALS_TIME";


    private static final String RECIPE_COMPONENTS_TABLE = "RECIPE_COMPONENTS_TABLE";
    private static final String PORTION_SIZE = "PORTION_SIZE";

    private static final String SAVED_RECIPE_COMPONENTS_TABLE = "SAVED_RECIPE_COMPONENTS_TABLE";

    private static final String WEIGHT_TABLE = "WEIGHT_TABLE";
    private static final String WEIGHT_VALUE = "WEIGHT_VALUE";
    private static final String WEIGHT_DATE = "WEIGHT_DATE";
    private static final String WEIGHT_TIME = "WEIGHT_TIME";


    private static final String DATABASE_NAME = "weight_tracker_database";


    private HelperDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //singleton design pattern for the DataBaseHelper
    public static synchronized HelperDataBase getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new HelperDataBase(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserTableString = "" +
                "CREATE TABLE " + USERS_TABLE + "(" +
                USERNAME + " varchar(20) NOT NULL, " +
                DAILY_CARBS_RATIO + " INTEGER, " +
                DAILY_FAT_RATIO + " INTEGER, " +
                DAILY_PROTEIN_RATIO + " INTEGER, " +
                USER_WEIGHT_TARGET + " REAL, " +
                USE_KILOJOULES + " CHAR(3)," +
                MONDAY_ENERGY_TARGET + " INTEGER, " +
                TUESDAY_ENERGY_TARGET + " INTEGER, " +
                WEDNESDAY_ENERGY_TARGET + " INTEGER, " +
                THURSDAY_ENERGY_TARGET + " INTEGER, " +
                FRIDAY_ENERGY_TARGET + " INTEGER, " +
                SATURDAY_ENERGY_TARGET + " INTEGER, " +
                SUNDAY_ENERGY_TARGET + " INTEGER, " +
                "PRIMARY KEY (" + USERNAME + "));";

        String createRecipeTableString = "" +
                "CREATE TABLE " + RECIPE_TABLE + "(" +
                RECIPE_ID + " varchar(24) NOT NULL PRIMARY KEY, " +
                RECIPE_NAME + " varchar(24) " +
                ");";

        String createSavedRecipeTableString = "" +
                "CREATE TABLE " + SAVED_RECIPE_TABLE + "(" +
                SAVED_RECIPE_NAME + " varchar(24) NOT NULL PRIMARY KEY" +
                ");";


        String createFoodTypeTableString = "" +
                "CREATE TABLE " + FOOD_TYPE_TABLE + "(" +
                FOOD_TYPE_NAME + " varchar(20) NOT NULL, " +
                FOOD_TYPE_BRAND + " varchar(20) NOT NULL, " +
                FOOD_TYPE_UNITS + " CHAR(5), " +
                FOOD_TYPE_KJ + " REAL, " +
                FOOD_TYPE_CARBS + " REAL, " +
                FOOD_TYPE_SUGARS + " REAL, " +
                FOOD_TYPE_PROTEIN + " REAL, " +
                FOOD_TYPE_FAT + " REAL, " +
                FOOD_TYPE_SERVING_SIZE + " REAL, " +
                FOOD_TYPE_SERVING_LABEL + " varchar(20) ," +
                "PRIMARY KEY (" + FOOD_TYPE_NAME + ", " + FOOD_TYPE_BRAND + ")" +
                ");";

        String createWeightTableString = "" +
                "CREATE TABLE " + WEIGHT_TABLE + "(" +
                WEIGHT_DATE + " CHAR(10) NOT NULL, " +
                WEIGHT_TIME + " CHAR(8) NOT NULL, " +
                WEIGHT_VALUE + "  REAL NOT NULL, " +
                USERNAME + " varchar(20) NOT NULL, " +
                "PRIMARY KEY (" + WEIGHT_TIME + ", " + WEIGHT_TIME + ", " + USERNAME + ")," +
                "FOREIGN KEY (" + USERNAME + ") REFERENCES " + USERS_TABLE + " (" + USERNAME + ")" +
                ");";

        String createRecipeComponentTableString = "" +
                "CREATE TABLE " + RECIPE_COMPONENTS_TABLE + "(" +
                FOOD_TYPE_NAME + " varchar(20) NOT NULL , " +
                FOOD_TYPE_BRAND + " varchar(20) NOT NULL , " +
                RECIPE_ID + " varchar(24) NOT NULL , " +
                PORTION_SIZE + " INTEGER NOT NULL , " +
                "PRIMARY KEY (" + FOOD_TYPE_NAME + ", " + FOOD_TYPE_BRAND + ", " + RECIPE_ID + ")," +
                "FOREIGN KEY (" + FOOD_TYPE_NAME + ") REFERENCES " + FOOD_TYPE_TABLE + " (" + FOOD_TYPE_NAME + ")," +
                "FOREIGN KEY (" + FOOD_TYPE_BRAND + ") REFERENCES " + FOOD_TYPE_TABLE + " (" + FOOD_TYPE_BRAND + ")," +
                "FOREIGN KEY (" + RECIPE_ID + ") REFERENCES " + RECIPE_TABLE + " (" + RECIPE_ID + ")" +
                ");";

        String createSavedRecipeComponentTableString = "" +
                "CREATE TABLE " + SAVED_RECIPE_COMPONENTS_TABLE + "(" +
                FOOD_TYPE_NAME + " varchar(20) NOT NULL , " +
                FOOD_TYPE_BRAND + " varchar(20) NOT NULL , " +
                SAVED_RECIPE_NAME + " varchar(24) NOT NULL , " +
                PORTION_SIZE + " INTEGER NOT NULL , " +
                "PRIMARY KEY (" + FOOD_TYPE_NAME + ", " + FOOD_TYPE_BRAND + ", " + SAVED_RECIPE_NAME + ")," +
                "FOREIGN KEY (" + FOOD_TYPE_NAME + ") REFERENCES " + FOOD_TYPE_TABLE + " (" + FOOD_TYPE_NAME + ")," +
                "FOREIGN KEY (" + FOOD_TYPE_BRAND + ") REFERENCES " + FOOD_TYPE_TABLE + " (" + FOOD_TYPE_BRAND + ")," +
                "FOREIGN KEY (" + SAVED_RECIPE_NAME + ") REFERENCES " + SAVED_RECIPE_TABLE + " (" + SAVED_RECIPE_NAME + ")" +
                ");";


        String createMealTableString = "" +
                "CREATE TABLE " + MEALS_TABLE + "(" +
                MEALS_DATE + " char(10) NOT NULL , " +
                MEALS_TIME + " char(8) NOT NULL , " +
                USERNAME + " varchar(20) NOT NULL, " +
                RECIPE_ID + " varchar(24) NOT NULL, " +
                "PRIMARY KEY (" + MEALS_DATE + ", " + MEALS_TIME + ", " + USERNAME + ", " + RECIPE_ID + ")," +
                "FOREIGN KEY (" + RECIPE_ID + ") REFERENCES " + RECIPE_TABLE + " (" + RECIPE_ID + ")," +
                "FOREIGN KEY (" + USERNAME + ") REFERENCES " + USERS_TABLE + " (" + USERNAME + ")" +
                ");";

        db.execSQL(createUserTableString);
        db.execSQL(createRecipeTableString);
        db.execSQL(createFoodTypeTableString);
        db.execSQL(createWeightTableString);
        db.execSQL(createRecipeComponentTableString);
        db.execSQL(createMealTableString);
        db.execSQL(createSavedRecipeTableString);
        db.execSQL(createSavedRecipeComponentTableString);



    }

    private Error insertDefaultUser() {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USERNAME, DEFAULT_USER);
            contentValues.put(USE_KILOJOULES, DEFAULT_ENERGY_UNIT);
            database.insertOrThrow(USERS_TABLE, null, contentValues);
            return null;
        } catch (
                Error e) {
            return e;
        }

    }


    public Error insertMealEntry(ClassRecipe recipe){
        try {



            return null;
        }
        catch (Error e){
            return e;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertWeightEntry(float weight, String time, String date) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WEIGHT_TIME, weight);
        contentValues.put(WEIGHT_DATE, date);
        contentValues.put(WEIGHT_TIME, time);
        contentValues.put(USERNAME, DEFAULT_USER);
        database.insert(WEIGHT_TABLE, null, contentValues);
    }

    public Error insertFoodItem(String name, String brand, String units, float kj, float carbs, float sugars, float protein, float fat, float servingSize, String servingLabelString) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(FOOD_TYPE_NAME, name);
            contentValues.put(FOOD_TYPE_BRAND, brand);
            contentValues.put(FOOD_TYPE_UNITS, units);
            contentValues.put(FOOD_TYPE_KJ, kj);
            contentValues.put(FOOD_TYPE_CARBS, carbs);
            contentValues.put(FOOD_TYPE_SUGARS, sugars);
            contentValues.put(FOOD_TYPE_PROTEIN, protein);
            contentValues.put(FOOD_TYPE_FAT, fat);
            contentValues.put(FOOD_TYPE_SERVING_SIZE, servingSize);
            contentValues.put(FOOD_TYPE_SERVING_LABEL, servingLabelString);
            database.insertOrThrow(FOOD_TYPE_TABLE, null, contentValues);
            return null;
        } catch (Error e) {
            return e;
        }

    }


    public void insertSavedRecipe(ClassRecipe recipe, String recipeName) {
        SQLiteDatabase database = this.getWritableDatabase();
        //insert saved recipe components loop
        for (int i = 0; i < recipe.size(); i++) {
            ClassIngredient ingredient = recipe.getIngredientDataAtIndex(i);
            ContentValues componentValues = new ContentValues();
            componentValues.put(FOOD_TYPE_NAME, ingredient.getFoodName());
            componentValues.put(FOOD_TYPE_BRAND, ingredient.getBrandName());
            componentValues.put(SAVED_RECIPE_NAME, recipeName);
            componentValues.put(PORTION_SIZE, ingredient.getAmount());
            database.insertOrThrow(SAVED_RECIPE_TABLE, null, componentValues);
        }
        ContentValues savedRecipeValues = new ContentValues();
        savedRecipeValues.put(SAVED_RECIPE_NAME, recipeName);
        database.insertOrThrow(SAVED_RECIPE_TABLE, null, savedRecipeValues);
    }


    public void insertMeal(ClassRecipe recipe, String recipeName) {
        SQLiteDatabase database = this.getWritableDatabase();
        //insert recipe components loop
        for (int i = 0; i < recipe.size(); i++) {
            ClassIngredient ingredient = recipe.getIngredientDataAtIndex(i);
            ContentValues componentValues = new ContentValues();
            componentValues.put(FOOD_TYPE_NAME, ingredient.getFoodName());
            componentValues.put(FOOD_TYPE_BRAND, ingredient.getBrandName());
            componentValues.put(PORTION_SIZE, ingredient.getAmount());
            database.insertOrThrow(RECIPE_TABLE, null, componentValues);
        }
        ContentValues recipeValues = new ContentValues();
        recipeValues.put(RECIPE_NAME, recipeName);
        database.insertOrThrow(RECIPE_TABLE, null, recipeValues);
    }


    public ClassFoodTypes getAllFoodItems() {
        SQLiteDatabase database = this.getWritableDatabase();
        ClassFoodTypes foodItems = new ClassFoodTypes();
        String query = "SELECT  *  from " + FOOD_TYPE_TABLE;

        try (Cursor cursor = database.rawQuery(query, null)) {
            while (cursor.moveToNext()) {
                ClassFoodType currentItem = new ClassFoodType(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getFloat(4),
                        cursor.getFloat(5), cursor.getFloat(6),
                        cursor.getFloat(7), cursor.getInt(8),
                        cursor.getString(9));

                foodItems.add(currentItem);
            }
        }
        return foodItems;
    }

    public boolean editUserSettings()
    {
        return true;
    }



    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
