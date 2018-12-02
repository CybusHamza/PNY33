package com.pnytrainings.myapplication.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DataBase {

    // database helper
    private DbHelper dbHelper;

    // context of activity
    private Context context;

    //Object of sqlite
    private SQLiteDatabase database;

    //constructor
    public DataBase(Context c) {
        context = c;
    }

    // Function to open Database
    public DataBase open() throws SQLException {
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    // Function to Close Database
    public void close() {
        dbHelper.close();
    }



    // For insertion in database
    public void insert(String first_name, String phoneNum) {

        ContentValues contentValue = new ContentValues();

        contentValue.put(DbHelper.KEY_NAME, first_name);
        contentValue.put(DbHelper.KEY_PH_NO, phoneNum);

        long result = database.insert(DbHelper.TABLE_NAME, null, contentValue);

    }

    // Retrive data Form DataBase

    public Cursor fetch() {

        String[] columns = new String[]{DbHelper.KEY_ID, DbHelper.KEY_NAME, DbHelper.KEY_PH_NO};
        Cursor cursor = database.query(DbHelper.TABLE_NAME, columns,null, null, null, null, null);
        cursor.moveToFirst();

        return cursor;
    }

    // Update in data base

    public int update(long _id, String first_name, String phoneNum) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.KEY_NAME, first_name);
        contentValues.put(DbHelper.KEY_PH_NO, phoneNum);

        int i = database.update(DbHelper.TABLE_NAME, contentValues,
                DbHelper.KEY_ID + " = " + _id, null);
        return i;
    }

    // delet From DataBase

    public void delete(long _id) {
        database.delete(DbHelper.TABLE_NAME, DbHelper.KEY_ID + "=" + _id, null);
    }




}
