package com.example.g_list;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


//SAMPLE DATA WILL NEED TO BE ADDED ALREADY.
public class ContactDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "grocery_list.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String CREATE_TABLE_KROGER =
            "create table kroger (kroger_id integer primary key autoincrement, "
                    + "kroger_product_name text not null, product_photo blob);";
    private static final String CREATE_TABLE_PUBLIX =
            "create table publix (publix_id integer primary key autoincrement, "
                    + "publix_product_name text not null unique, product_photo blob);";
    private static final String CREATE_TABLE_ALL_LIST =
            "create table all_list(user_list_id integer primary key autoincrement, "
                    + "list_name text not null, date_created text);";


    public ContactDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_KROGER);
        database.execSQL(CREATE_TABLE_PUBLIX);
        database.execSQL(CREATE_TABLE_ALL_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Decide what to do on upgrade
    }
}

