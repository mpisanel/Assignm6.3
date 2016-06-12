package com.pharmacy.repository.alternativecontact.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pharmacy.conf.databases.DBConstants;
import com.pharmacy.domain.alternativecontact.impl.AlternativeContactImpl;
import com.pharmacy.repository.alternativecontact.AlternativeContactRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class AlternativeContactRepositoryImpl extends SQLiteOpenHelper implements AlternativeContactRepository
{
    private static final String TABLE_NAME = "AlternativeContact.db";
    SQLiteDatabase db;

    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_NUMBER = "NUMBER ";

    //create sql statement
    private static final String SETTINGS = "SETTINGS" + TABLE_NAME + "("
            +COL_ID+ "INTEGER PRIMARY KEY AUTOINCREAMENT "
            +COL_NAME+ "TEXT UNIQUE NOT NULL"
            +COL_NUMBER+ "TEXT UNIQUE NOT NULL)";

    public AlternativeContactRepositoryImpl(Context context)
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

    @Override
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
    public AlternativeContactImpl findById(Long aLong)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COL_ID,
                        COL_NAME,
                        COL_NUMBER},
                COL_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final AlternativeContactImpl alternativeContact = new AlternativeContactImpl.Builder()
                    .bAleternID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                    .bAlternName(cursor.getString(cursor.getColumnIndex(COL_NAME)))
                    .bAlternNumber(cursor.getString(cursor.getColumnIndex(COL_NUMBER)))
                    .build();
            return alternativeContact;
        } else {
            return null;
        }
    }

    @Override
    public AlternativeContactImpl save(AlternativeContactImpl entity)
    {
        open();
        ContentValues values = new ContentValues();

        values.put(COL_ID, entity.getAlternID());
        values.put(COL_NAME, entity.getAlternName());
        values.put(COL_NUMBER, entity.getAlternativeContactNumber());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        AlternativeContactImpl insertedEntity = new AlternativeContactImpl.Builder()
                .copy(entity)
                .bAleternID(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public AlternativeContactImpl update(AlternativeContactImpl entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COL_ID, entity.getAlternID());
        values.put(COL_NAME, entity.getAlternName());
        values.put(COL_NUMBER, entity.getAlternativeContactNumber());
        db.update(
                TABLE_NAME,
                values,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getAlternID())}
        );
        return entity;
    }

    @Override
    public AlternativeContactImpl delete(AlternativeContactImpl entity)
    {
        open();
        db.delete(
                TABLE_NAME,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getAlternID())});
        return entity;
    }

    @Override
    public Set<AlternativeContactImpl> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<AlternativeContactImpl> alterContacts = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final AlternativeContactImpl alternativeContact = new AlternativeContactImpl.Builder()
                        .bAleternID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                        .bAlternName(cursor.getString(cursor.getColumnIndex(COL_NAME)))
                        .bAlternNumber(cursor.getString(cursor.getColumnIndex(COL_NUMBER)))
                        .build();
                alterContacts.add(alternativeContact);
            } while (cursor.moveToNext());
        }
        return alterContacts;
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
