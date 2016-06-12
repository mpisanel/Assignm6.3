package com.pharmacy.repository.pharmacy.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pharmacy.conf.databases.DBConstants;
import com.pharmacy.domain.pharmacy.impl.PharmacyImpl;
import com.pharmacy.repository.pharmacy.PharmacyRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class PharmacyRepositoryImpl extends SQLiteOpenHelper implements PharmacyRepository
{
    private static final String TABLE_NAME = "Prescription.db";
    SQLiteDatabase db;

    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";

    //create sql statement
    private static final String SETTINGS = "SETTINGS" + TABLE_NAME + "("
            +COL_ID+ "INTEGER PRIMARY KEY AUTOINCREAMENT "
            +COL_NAME+ "TEXT UNIQUE NOT NULL)";

    public PharmacyRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }
    public void open() throws SQLException
    {
        db = this.getWritableDatabase();
    }
    public void close()
    {
        this.close();
    }


    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SETTINGS);
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
    public PharmacyImpl findById(Long aLong)
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
            final PharmacyImpl pharmacy = new PharmacyImpl.Builder()
                    .bPharmacyID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                    .bPharmName(cursor.getString(cursor.getColumnIndex(COL_NAME)))
                    .build();
            return pharmacy;
        } else {
            return null;
        }
    }

    @Override
    public PharmacyImpl save(PharmacyImpl entity)
    {
        open();
        ContentValues values = new ContentValues();

        values.put(COL_ID, entity.getPharmacyID());
        values.put(COL_NAME, entity.getPharmacyName());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        PharmacyImpl  insertedEntity = new PharmacyImpl.Builder()
                .copy(entity)
                .bPharmacyID(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public PharmacyImpl update(PharmacyImpl entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COL_ID, entity.getPharmacyID());
        values.put(COL_NAME, entity.getPharmacyName());
        db.update(
                TABLE_NAME,
                values,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getPharmacyID())}
        );
        return entity;
    }

    @Override
    public PharmacyImpl delete(PharmacyImpl entity)
    {
        open();
        db.delete(
                TABLE_NAME,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getPharmacyID())});
        return entity;
    }

    @Override
    public Set<PharmacyImpl> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PharmacyImpl> pharmacySet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PharmacyImpl prescription = new PharmacyImpl.Builder()
                        .bPharmacyID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                        .bPharmName(cursor.getString(cursor.getColumnIndex(COL_NAME)))
                        .build();
                pharmacySet.add(prescription);
            } while (cursor.moveToNext());
        }
        return pharmacySet;
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
