package com.war.babyandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final String TEXT_TYPE = "TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StringDB.TABLE_NAME + " (" +
                    StringDB._ID + " INTEGER PRIMARY KEY," +
                    StringDB.COLUMN_NAME_VALUE + " " + TEXT_TYPE +
            " )";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Stringer.db";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StringDB.TABLE_NAME;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("BabyAndroid", SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertString(String value){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(StringDB.COLUMN_NAME_VALUE, value);

        db.insert(StringDB.TABLE_NAME, null, contentValues);

    }

    public ArrayList<String> readString(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> list = new ArrayList<>();

        Cursor cursor = db.rawQuery( "select * from " + StringDB.TABLE_NAME, null );

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            list.add(cursor.getString(cursor.getColumnIndexOrThrow(StringDB.COLUMN_NAME_VALUE)));
            cursor.moveToNext();
        }

        cursor.close();

        return list;

    }



}
