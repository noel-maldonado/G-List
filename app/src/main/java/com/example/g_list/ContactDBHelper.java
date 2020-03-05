package com.example.g_list;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "grocery_list.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String CREATE_TABLE_KROGER =
            "create table kroger (kroger_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "product TEXT NOT NULL, product_photo BLOB, price TEXT NOT NULL);";

    private static final String CREATE_TABLE_PUBLIX =
            "create table publix (publix_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "product TEXT NOT NULL, product_photo BLOB, price TEXT NOT NULL);";

    private static final String CREATE_TABLE_ALL_LIST =
            "create table lists(list_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "list_name TEXT NOT NULL, date_created TEXT NOT NULL);";

    private static final String CREATE_TABLE_KROGER_LIST =
            "create table kroger_list(list_id INTEGER, kroger_id INTEGER, quantity INTEGER DEFAULT 0, " +
                    "FOREIGN KEY (list_id) REFERENCES lists(list_id)," +
                    "FOREIGN KEY (kroger_id) REFERENCES kroger(kroger_id)" +
                    ");";

    private static final String CREATE_TABLE_PUBLIX_LIST =
            "create table publix_list(list_id INTEGER, publix_id INTEGER, quantity INTEGER DEFAULT 0, " +
                    "FOREIGN KEY (list_id) REFERENCES lists(list_id)," +
                    "FOREIGN KEY (publix_id) REFERENCES publix(publix_id)" +
                    ");";

    public ContactDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_KROGER);
        database.execSQL(CREATE_TABLE_PUBLIX);
        database.execSQL(CREATE_TABLE_ALL_LIST);
        database.execSQL(CREATE_TABLE_KROGER_LIST);
        database.execSQL(CREATE_TABLE_PUBLIX_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Decide what to do on upgrade
    }
}

