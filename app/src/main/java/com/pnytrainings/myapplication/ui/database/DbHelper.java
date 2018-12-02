package com.pnytrainings.myapplication.ui.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PNY Trainings on 12/2/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    // Database Information
    static final String DB_NAME = "MYDATABASE.DB";

    // database version
    static final int DB_VERSION = 1;

    static final String TABLE_NAME = "User";

    //columns
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PH_NO = "phone_number";


    // DDL data defining language.
    String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + KEY_NAME + " TEXT,"
            + KEY_PH_NO + " TEXT" + ")";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
