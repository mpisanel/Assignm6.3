package com.pharmacy.repository.doctor.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pharmacy.conf.databases.DBConstants;
import com.pharmacy.domain.doctor.impl.DoctorImp;
import com.pharmacy.repository.doctor.DoctorRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class DoctorRepositoryImpl extends SQLiteOpenHelper implements DoctorRepository
{
    private static final String TABLE_NAME = "doctor";
    private SQLiteDatabase db;

    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";

    //create sql statement
   /* private static final String SETTINGS = "SETTINGS" + TABLE_NAME + "("
            +COL_ID+ "INTEGER PRIMARY KEY AUTOINCREAMENT "
            +COL_NAME+ "TEXT UNIQUE NOT NULL)";*/

    private static final String DATABASE_CREATE = " CREATE TABLE "
            +TABLE_NAME + "("
            +COL_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            +COL_NAME + " TEXT NOT NULL );";






    public DoctorRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null,
                DBConstants.DATABASE_VERSION);
    }
    public void open() throws SQLException
    {
        db = this.getWritableDatabase();
    }
    public void close()
    {
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public DoctorImp findById(Long aLong)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COL_ID,
                        COL_NAME},
                COL_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final DoctorImp doctorImp = new DoctorImp.Builder()
                    .bDoctorID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                    .bDocName(cursor.getString(cursor.getColumnIndex(COL_NAME)))
                    .build();
            return doctorImp;
        } else {
            return null;
        }
    }

    @Override
    public DoctorImp save(DoctorImp entity)
    {
        open();
        ContentValues values = new ContentValues();

        values.put(COL_ID, entity.getDoctorID());
        values.put(COL_NAME, entity.getDoctorName());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        DoctorImp insertedEntity = new DoctorImp.Builder()
                .copy(entity)
                .bDoctorID(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public DoctorImp update(DoctorImp entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COL_ID, entity.getDoctorID());
        values.put(COL_NAME, entity.getDoctorName());;
        db.update(
                TABLE_NAME,
                values,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getDoctorID())}
        );
        return entity;
    }

    @Override
    public DoctorImp delete(DoctorImp entity)
    {
        open();
        db.delete(
                TABLE_NAME,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getDoctorID())});
        return entity;
    }

    @Override
    public Set<DoctorImp> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<DoctorImp> doctorImpSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final DoctorImp doctorImp = new DoctorImp.Builder()
                        .bDoctorID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                        .bDocName(cursor.getString(cursor.getColumnIndex(COL_NAME)))
                        .build();
                doctorImpSet.add(doctorImp);
            } while (cursor.moveToNext());
        }
        return doctorImpSet;
    }

    @Override
    public int deleteAll()
    {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
