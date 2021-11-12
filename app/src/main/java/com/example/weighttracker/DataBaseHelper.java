package com.example.weighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static DataBaseHelper sInstance;

    private static String default_user = "Greg Quinn";


    //User table
    private static final String USERS_TABLE = "USERS_TABLE";
    private static final String USERNAME = "USERNAME";
    private static final String USER_WEIGHT_TARGET = "USER_WEIGHT_TARGET";
    private static final String DAILY_CARBS_GOAL = "DAILY_CARBS_GOAL";
    private static final String DAILY_SUGARS_GOAL = "DAILY_SUGARS_GOAL";
    private static final String DAILY_PROTEIN_GOAL = "DAILY_PROTEIN_GOAL";
    private static final String DAILY_FAT_GOAL = "DAILY_FAT_GOAL";

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
    private static final String ISSAVED = "ISSAVED";

    //Meal table
    private static final String MEALS_TABLE = "MEALS_TABLE";
    private static final String MEALS_DATE = "MEALS_DATE";
    private static final String MEALS_TIME = "MEALS_TIME";


    private static final String RECIPE_COMPONENTS_TABLE = "RECIPE_COMPONENTS_TABLE";
    private static final String PORTION_SIZE = "PORTION_SIZE";


    private static final String WEIGHT_TABLE = "WEIGHT_TABLE";
    private static final String WEIGHT_VALUE = "WEIGHT_VALUE";
    private static final String WEIGHT_DATE = "WEIGHT_DATE";
    private static final String WEIGHT_TIME = "WEIGHT_TIME";


    private static final String DATABASE_NAME = "weight_tracker_database";


    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //singleton design pattern for the DataBaseHelper
    public static synchronized DataBaseHelper getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new DataBaseHelper(context.getApplicationContext());
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
                DAILY_CARBS_GOAL + " int, " +
                DAILY_SUGARS_GOAL + " int, " +
                DAILY_FAT_GOAL + " int, " +
                DAILY_PROTEIN_GOAL + " int, " +
                USER_WEIGHT_TARGET + " int, " +
                "PRIMARY KEY (" + USERNAME + "));";

        String createRecipeTableString = "" +
                "CREATE TABLE " + RECIPE_TABLE + "(" +
                RECIPE_NAME + " varchar(24) NOT NULL PRIMARY KEY, " +
                ISSAVED + "  BOOL NOT NULL " +
                ");";


        String createFoodTypeTableString = "" +
                "CREATE TABLE " + FOOD_TYPE_TABLE + "(" +
                FOOD_TYPE_NAME + " varchar(20) NOT NULL, " +
                FOOD_TYPE_BRAND + " varchar(20) NOT NULL, " +
                FOOD_TYPE_UNITS + " CHAR(5), " +
                FOOD_TYPE_KJ + " int, " +
                FOOD_TYPE_CARBS + " int, " +
                FOOD_TYPE_SUGARS + " int, " +
                FOOD_TYPE_PROTEIN + " int, " +
                FOOD_TYPE_FAT + " int, " +
                FOOD_TYPE_SERVING_SIZE + " int, " +
                FOOD_TYPE_SERVING_LABEL + " varchar(20) ," +
                "PRIMARY KEY (" + FOOD_TYPE_NAME + ", " + FOOD_TYPE_BRAND + ")" +
                ");";

        String createWeightTableString = "" +
                "CREATE TABLE " + WEIGHT_TABLE + "(" +
                WEIGHT_DATE + " CHAR(10) NOT NULL, " +
                WEIGHT_TIME + " CHAR(8) NOT NULL, " +
                WEIGHT_VALUE + "  int NOT NULL, " +
                USERNAME + " varchar(20) NOT NULL, " +
                "PRIMARY KEY (" + WEIGHT_TIME + ", " + WEIGHT_TIME + ", " + USERNAME + ")," +
                "FOREIGN KEY (" + USERNAME + ") REFERENCES " + USERS_TABLE + " (" + USERNAME + ")" +
                ");";

        String createRecipeComponentTableString = "" +
                "CREATE TABLE " + RECIPE_COMPONENTS_TABLE + "(" +
                FOOD_TYPE_NAME + " varchar(20) NOT NULL , " +
                FOOD_TYPE_BRAND + " varchar(20) NOT NULL , " +
                RECIPE_NAME + " varchar(24) NOT NULL , " +
                PORTION_SIZE + " int NOT NULL , " +
                "PRIMARY KEY (" + FOOD_TYPE_NAME + ", " + FOOD_TYPE_BRAND + ", " + RECIPE_NAME + ")," +
                "FOREIGN KEY (" + FOOD_TYPE_NAME + ") REFERENCES " + FOOD_TYPE_TABLE + " (" + FOOD_TYPE_NAME + ")," +
                "FOREIGN KEY (" + FOOD_TYPE_BRAND + ") REFERENCES " + FOOD_TYPE_TABLE + " (" + FOOD_TYPE_BRAND + ")," +
                "FOREIGN KEY (" + RECIPE_NAME + ") REFERENCES " + RECIPE_TABLE + " (" + RECIPE_NAME + ")" +
                ");";

        String createMealTableString = "" +
                "CREATE TABLE " + MEALS_TABLE + "(" +
                MEALS_DATE + " char(10) NOT NULL , " +
                MEALS_TIME + " char(8) NOT NULL , " +
                USERNAME + " varchar(20) NOT NULL, " +
                RECIPE_NAME + " varchar(24) NOT NULL, " +
                "PRIMARY KEY (" + MEALS_DATE + ", " + MEALS_TIME + ", " + USERNAME + ", " + RECIPE_NAME + ")," +
                "FOREIGN KEY (" + RECIPE_NAME + ") REFERENCES " + RECIPE_TABLE + " (" + RECIPE_NAME + ")," +
                "FOREIGN KEY (" + USERNAME + ") REFERENCES " + USERS_TABLE + " (" + USERNAME + ")" +
                ");";

        db.execSQL(createUserTableString);
        db.execSQL(createRecipeTableString);
        db.execSQL(createFoodTypeTableString);
        db.execSQL(createWeightTableString);
        db.execSQL(createRecipeComponentTableString);
        db.execSQL(createMealTableString);


    }

    private Error insertDefaultUser(SQLiteDatabase db) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USERNAME, default_user);
            database.insertOrThrow(USERS_TABLE, null, contentValues);
            return null;
        } catch (
                Error e) {
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
        contentValues.put(USERNAME, default_user);
        database.insert(WEIGHT_TABLE, null, contentValues);
    }

    public Error insertFoodItem(String name, String brand, String units, float kj, float carbs, float protein, float fat, float servingSize) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(FOOD_TYPE_NAME, name);
            contentValues.put(FOOD_TYPE_BRAND, brand);
            contentValues.put(FOOD_TYPE_UNITS, units);
            contentValues.put(FOOD_TYPE_KJ, kj);
            contentValues.put(FOOD_TYPE_CARBS, carbs);
            contentValues.put(FOOD_TYPE_PROTEIN, protein);
            contentValues.put(FOOD_TYPE_FAT, fat);
            contentValues.put(FOOD_TYPE_SERVING_SIZE, servingSize);
            database.insertOrThrow(FOOD_TYPE_TABLE, null, contentValues);
            return null;
        } catch (Error e) {
            return e;
        }

    }


    public FoodTypes getAllFoodItems() {
        SQLiteDatabase database = this.getWritableDatabase();
        FoodTypes foodItems = new FoodTypes();
        String query = "SELECT  *  from " + FOOD_TYPE_TABLE;

        try (Cursor cursor = database.rawQuery(query, null)) {
            while (cursor.moveToNext()) {
                FoodType currentItem = new FoodType(cursor.getString(0),
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


    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
