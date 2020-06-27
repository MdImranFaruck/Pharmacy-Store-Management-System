package com.imran.familypharmacy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Basic database class for the application.
 * The only class that should use this is  {@link AppProvider}.
 * This class create all the database table for us
 */

class AppDatabase extends SQLiteOpenHelper {
    private static final String TAG = "AppDatabase";

    //Database name
    public static final String DATABASE_NAME = "FamilyPharmacy.db";
    //Database version
    public static final int DATABASE_VERSION = 8;
    // Implement AppDatabase as a Singleton
    private static AppDatabase instance = null;

    //This all field for Login Forum
    // We can not remove this field from here, because it is related to MainActivity
    public static final String TABLE_NAME = "Register";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FirstName";
    public static final String COL_3 = "LastName";
    public static final String COL_4 = "Password";
    public static final String COL_5 = "Email";
    public static final String COL_6 = "Phone";
    //Until here


    //This all field for Login Forum
    //We can not remove this field from here, because it is related to MainActivity
    public static final String TABLE_NAME_R = "Reports";
    public static final String COL_7 = "Pharmacy_ID";
    public static final String COL_8 = "Pharmacy_Location";
    public static final String COL_9 = "Pharmacist_Name";
    public static final String COL_10 = "Email";
    public static final String COL_11 = "Emergency_Note";
    public static final String COL_12 = "Subject";
    public static final String COL_13 = "Report_Details";
    //Until here

    //This all field for admin Login Forum
    public static final String TABLE_NAME_Admin = "Admin";
    public static final String COL_14 = "AdminName";
    public static final String COL_15 = "Password";
    //Until here


    //Before it was Private we make Public for Login Forum
    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "AppDatabase: constructor");
    }

    /**
     *This method help not create more then one instance in database, when already one exit
     * Get an instance of the app's singleton database helper object
     *
//     * @param context the content providers context.
     * @return a SQLite database helper object
     */
    static AppDatabase getInstance(Context context) {
        if(instance == null) {
            Log.d(TAG, "getInstance: creating new instance");
            instance = new AppDatabase(context);
        }

        return instance;
    }

    //This OnCreate Method for creating Table in the Database
    @Override
    public void onCreate(SQLiteDatabase db) {

//        This Line of code for Login forum, which create Register table
//        db.execSQL("CREATE TABLE "+TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FirstName TEXT, LastName TEXT, Password TEXT, Email TEXT, Phone TEXT)");

        //This code for creating Medicine Table in database
        String sSQL;
        sSQL = "CREATE TABLE " + MedicinesContract.TABLE_NAME + " ("
                + MedicinesContract.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
                + MedicinesContract.Columns.MEDICINES_NAME + " TEXT NOT NULL, "
                + MedicinesContract.Columns.MEDICINES_COMPANY + " TEXT, "
                + MedicinesContract.Columns.MEDICINES_LOCATION + " TEXT, "
                + MedicinesContract.Columns.MEDICINES_PRICE + " TEXT, "
                + MedicinesContract.Columns.MEDICINES_CATEGORY + " INTEGER NOT NULL);";
        Log.d(TAG, sSQL);
        db.execSQL(sSQL);

        //This code for creating Notification Table in database
//        String SQLTable = "CREATE TABLE Notifications (_ID INTEGER PRIMARY KEY NOT NULL, SenderName TEXT NOT NULL, Subject TEXT, Content TEXT NOT NULL, PharmacyID INTEGER NOT NULL);";
//        db.execSQL(SQLTable);

        //This Code for Reports Table in Database
//        String SQLTable = "CREATE TABLE Reports (_ID INTEGER PRIMARY KEY NOT NULL, " +
//                "Pharmacy_ID TEXT, Pharmacy_Location TEXT, " +
//                "Pharmacist_Name TEXT, Email TEXT, Emergency_Note TEXT, Subject TEXT, Report_Details TEXT );";
//        db.execSQL(SQLTable);

        //This Code for Admin Table
//        String SQLTable = "CREATE TABLE Admin (_ID INTEGER PRIMARY KEY NOT NULL, AdminName TEXT NOT NULL, BankAccount TEXT, Paypal TEXT NOT NULL, Password TEXT NOT NULL);";
//        db.execSQL(SQLTable);


        Log.d(TAG, "onCreate: ends");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: starts");
//        This two line code for Login forum
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//        Until here

        //THis is for Notification table
//        String SQLTable = "DROP TABLE IF EXISTS Notifications";
//        db.execSQL(SQLTable);
//        onCreate(db);
        //Until here

        //THis is for Reports table
//        String SQLTable = "DROP TABLE IF EXISTS Reports;";
//        db.execSQL(SQLTable);
//        onCreate(db);
        //Until here

        //THis is for Admin table
//        String SQLTable = "DROP TABLE IF EXISTS Admin;";
//        db.execSQL(SQLTable);
        onCreate(db);
        //Until here

        switch(oldVersion) {
            case 1:
                // upgrade logic from version 1
            default:
                throw new IllegalStateException("onUpgrade() with unknown newVersion: " + newVersion);
        }
    }
}
