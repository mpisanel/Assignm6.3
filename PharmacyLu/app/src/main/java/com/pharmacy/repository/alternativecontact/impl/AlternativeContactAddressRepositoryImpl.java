package com.pharmacy.repository.alternativecontact.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pharmacy.conf.databases.DBConstants;
import com.pharmacy.domain.alternativecontact.impl.AlternativeContactAddressImpl;
import com.pharmacy.repository.alternativecontact.AlternativeContactAddressRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class AlternativeContactAddressRepositoryImpl extends SQLiteOpenHelper implements AlternativeContactAddressRepository
{
    private static final String TABLE_NAME = "Alternative_Address.db";
    SQLiteDatabase db;

    private static final String COL_ID = "ID";
    private static final String COL_STREET = "STREET";
    private static final String COL_SUBURB = "SUBURB";
    private static final String COL_POST_CODE = "POST_CODE";


    //create sql statement
    private static final String SETTINGS = "SETTINGS" + TABLE_NAME + "("
            +COL_ID+ "INTEGER PRIMARY KEY AUTOINCREAMENT "
            +COL_STREET+ "TEXT UNIQUE NOT NULL"
            +COL_SUBURB+ "TEXT UNIQUE NOT NULL"
            +COL_POST_CODE+ "TEXT UNIQUE NOT NULL)";

    public AlternativeContactAddressRepositoryImpl(Context context)
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
    public AlternativeContactAddressImpl findById(Long aLong)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COL_ID,
                        COL_STREET,
                        COL_SUBURB,
                        COL_POST_CODE},
                COL_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final AlternativeContactAddressImpl doctorAddress = new AlternativeContactAddressImpl.Builder()
                    .bStreetID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                    .bStreet(cursor.getString(cursor.getColumnIndex(COL_STREET)))
                    .bSuburb(cursor.getString(cursor.getColumnIndex(COL_SUBURB)))
                    .bPostCode(cursor.getString(cursor.getColumnIndex(COL_POST_CODE)))
                    .build();
            return doctorAddress;
        } else {
            return null;
        }
    }

    @Override
    public AlternativeContactAddressImpl save(AlternativeContactAddressImpl entity)
    {
        open();
        ContentValues values = new ContentValues();

        values.put(COL_ID, entity.getStreetID());
        values.put(COL_STREET, entity.getStreet());
        values.put(COL_SUBURB, entity.getSuburb());
        values.put(COL_POST_CODE, entity.getPostCode());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        AlternativeContactAddressImpl insertedEntity = new AlternativeContactAddressImpl.Builder()
                .copy(entity)
                .bStreetID(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public AlternativeContactAddressImpl update(AlternativeContactAddressImpl entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COL_ID, entity.getStreetID());
        values.put(COL_STREET, entity.getStreet());
        values.put(COL_SUBURB, entity.getSuburb());
        values.put(COL_POST_CODE, entity.getPostCode());
        db.update(
                TABLE_NAME,
                values,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getStreetID())}
        );
        return entity;
    }

    @Override
    public AlternativeContactAddressImpl delete(AlternativeContactAddressImpl entity)
    {
        open();
        db.delete(
                TABLE_NAME,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getStreetID())});
        return entity;
    }

    @Override
    public Set<AlternativeContactAddressImpl> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<AlternativeContactAddressImpl> alternativeContactAddresses = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final AlternativeContactAddressImpl alternativeContactAddress = new AlternativeContactAddressImpl.Builder()
                        .bStreetID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                        .bStreet(cursor.getString(cursor.getColumnIndex(COL_STREET)))
                        .bSuburb(cursor.getString(cursor.getColumnIndex(COL_SUBURB)))
                        .bPostCode(cursor.getString(cursor.getColumnIndex(COL_POST_CODE)))
                        .build();
                alternativeContactAddresses.add(alternativeContactAddress);
            } while (cursor.moveToNext());
        }
        return alternativeContactAddresses;
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
