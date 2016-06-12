package com.pharmacy.repository.prescription.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pharmacy.conf.databases.DBConstants;
import com.pharmacy.domain.prescription.impl.PrescriptionImpl;
import com.pharmacy.repository.prescription.PrescriptionRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class PrescriptionRepositoryImpl extends SQLiteOpenHelper implements PrescriptionRepository
{
    private static final String TABLE_NAME = "Prescription.db";
    SQLiteDatabase db;

    private static final String COL_ID = "ID";
    private static final String COL_DATE = "DATE";

    //create sql statement
    private static final String SETTINGS = "SETTINGS" + TABLE_NAME + "("
            +COL_ID+ "INTEGER PRIMARY KEY AUTOINCREAMENT "
            +COL_DATE+ "TEXT UNIQUE NOT NULL)";

    public PrescriptionRepositoryImpl(Context context)
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
    public PrescriptionImpl findById(Long aLong)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COL_ID,
                        COL_DATE},
                COL_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final PrescriptionImpl prescription = new PrescriptionImpl.Builder()
                    .bPreID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                    .bPrescriptionDate(cursor.getString(cursor.getColumnIndex(COL_DATE)))
                    .build();
            return prescription;
        } else {
            return null;
        }
    }

    @Override
    public PrescriptionImpl save(PrescriptionImpl entity)
    {
        open();
        ContentValues values = new ContentValues();

        values.put(COL_ID, entity.getPrescriptionID());
        values.put(COL_DATE, entity.getPrescriptionDate());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        PrescriptionImpl  insertedEntity = new PrescriptionImpl.Builder()
                .copy(entity)
                .bPreID(new Long(id))
                .build();

        return insertedEntity;

    }

    @Override
    public PrescriptionImpl update(PrescriptionImpl entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COL_ID, entity.getPrescriptionID());
        values.put(COL_DATE, entity.getPrescriptionDate());
        db.update(
                TABLE_NAME,
                values,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getPrescriptionID())}
        );
        return entity;
    }

    @Override
    public PrescriptionImpl delete(PrescriptionImpl entity)
    {
        open();
        db.delete(
                TABLE_NAME,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getPrescriptionID())});
        return entity;
    }

    @Override
    public Set<PrescriptionImpl> findAll()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<PrescriptionImpl> prescriptionSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PrescriptionImpl prescription = new PrescriptionImpl.Builder()
                        .bPreID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                        .bPrescriptionDate(cursor.getString(cursor.getColumnIndex(COL_DATE)))
                        .build();
                prescriptionSet.add(prescription);
            } while (cursor.moveToNext());
        }
        return prescriptionSet;
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
