package com.pharmacy.repository.patient.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.util.Log;

import com.pharmacy.conf.databases.DBConstants;
import com.pharmacy.domain.patient.Patient;
import com.pharmacy.domain.patient.impl.PatientImpl;
import com.pharmacy.repository.patient.PatientRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class PatientRepositoryImpl extends SQLiteOpenHelper implements PatientRepository
{
    private static final String TABLE_NAME = "Patient.db";
    SQLiteDatabase db;

    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_MED_ID = "MED_ID";
    private static final String COL_MED_NAME = "MED_NAME";

    //create sql statement
    private static final String SETTINGS = "SETTINGS" + TABLE_NAME + "("
            +COL_ID+ "INTEGER PRIMARY KEY AUTOINCREMENT "
            +COL_NAME+ "TEXT UNIQUE NOT NULL"
            +COL_MED_ID+ "TEXT UNIQUE NOT NULL"
            +COL_MED_NAME+ "TEXT UNIQUE NOT NULL)";

    public PatientRepositoryImpl(Context context) {
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
    public PatientImpl findById(Long aLong)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COL_ID,
                        COL_NAME,
                        COL_MED_ID,
                        COL_MED_NAME},
                COL_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final PatientImpl patient = new PatientImpl.Builder()
                    .bPatID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                    .bPatName(cursor.getString(cursor.getColumnIndex(COL_NAME)))
                    .bMedID(cursor.getString(cursor.getColumnIndex(COL_MED_ID)))
                    .bMedName(cursor.getString(cursor.getColumnIndex(COL_MED_NAME)))
                    .build();
            return patient;
        } else {
            return null;
        }
    }

    @Override
    public PatientImpl save(PatientImpl entity)
    {
        open();
        ContentValues values = new ContentValues();

        values.put(COL_ID, entity.getPatientID());
        values.put(COL_NAME, entity.getPatientName());
        values.put(COL_MED_ID, entity.getMedicalID());
        values.put(COL_NAME, entity.getMedicalName());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        PatientImpl insertedEntity = new PatientImpl.Builder()
                .copy(entity)
                .bPatID(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public PatientImpl update(PatientImpl entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COL_ID, entity.getPatientID());
        values.put(COL_NAME, entity.getPatientName());
        values.put(COL_MED_ID, entity.getMedicalID());
        values.put(COL_MED_NAME, entity.getMedicalName());
        db.update(
                TABLE_NAME,
                values,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getPatientID())}
        );
        return entity;
    }

    @Override
    public PatientImpl delete(PatientImpl entity)
    {
        open();
        db.delete(
                TABLE_NAME,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getPatientID())});
        return entity;
    }

    @Override
    public Set<PatientImpl> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PatientImpl> patientSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PatientImpl patient = new PatientImpl.Builder()
                        .bPatID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                        .bPatName(cursor.getString(cursor.getColumnIndex(COL_NAME)))
                        .bMedID(cursor.getString(cursor.getColumnIndex(COL_MED_ID)))
                        .bMedName(cursor.getString(cursor.getColumnIndex(COL_MED_NAME)))
                        .build();
                patientSet.add(patient);
            } while (cursor.moveToNext());
        }
        return patientSet;
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
