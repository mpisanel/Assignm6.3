package com.pharmacy.conf.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SONY on 2016-06-06.
 */
public class DBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "PatientDB.db";
    public static final String TABLE_NAME = "patient_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(COL2, name);

        long result = db.insert(TABLE_NAME, null, contentValue);
        if(result == -1)
        {
            return false;
        }
        else
            return  true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return  res;
    }


}
