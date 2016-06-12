package com.pharmacy.repository.invoice.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pharmacy.conf.databases.DBConstants;
import com.pharmacy.domain.invoice.impl.InvoiceImpl;
import com.pharmacy.repository.invoice.InvoiceRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class InvoiceRepositoryImpl  extends SQLiteOpenHelper implements InvoiceRepository
{
    private static final String TABLE_NAME = "Invoice.db";
    SQLiteDatabase db;

    private static final String COL_ID = "ID";
    private static final String COL_DETAILS = "DETAILS";
    private static final String COL_DATE = "DATE";

    //create sql statement
    private static final String SETTINGS = "SETTINGS" + TABLE_NAME + "("
            +COL_ID+ "INTEGER PRIMARY KEY AUTOINCREAMENT "
            +COL_DETAILS+ "TEXT UNIQUE NOT NULL"
            +COL_DATE+ "TEXT UNIQUE NOT NULL)";

    public InvoiceRepositoryImpl(Context context)
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
    public InvoiceImpl findById(Long aLong)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COL_ID,
                        COL_DETAILS,
                        COL_DATE},
                COL_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final InvoiceImpl prescription = new InvoiceImpl.Builder()
                    .bInvoiceID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                    .bDetails(cursor.getString(cursor.getColumnIndex(COL_DETAILS)))
                    .bCurrentDate(cursor.getString(cursor.getColumnIndex(COL_DATE)))
                    .build();
            return prescription;
        } else {
            return null;
        }
    }

    @Override
    public InvoiceImpl save(InvoiceImpl entity)
    {
        open();
        ContentValues values = new ContentValues();

        values.put(COL_ID, entity.getInvoiceID());
        values.put(COL_DETAILS, entity.getDetails());
        values.put(COL_DATE, entity.getCurrentDate());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        InvoiceImpl  insertedEntity = new InvoiceImpl.Builder()
                .copy(entity)
                .bInvoiceID(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public InvoiceImpl update(InvoiceImpl entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COL_ID, entity.getInvoiceID());
        values.put(COL_DETAILS, entity.getDetails());
        values.put(COL_DATE, entity.getCurrentDate());

        db.update(
                TABLE_NAME,
                values,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getInvoiceID())}
        );
        return entity;
    }

    @Override
    public InvoiceImpl delete(InvoiceImpl entity)
    {
        open();
        db.delete(
                TABLE_NAME,
                COL_ID + " =? ",
                new String[]{String.valueOf(entity.getInvoiceID())});
        return entity;
    }

    @Override
    public Set<InvoiceImpl> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<InvoiceImpl> invoiceSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final InvoiceImpl invoice = new InvoiceImpl.Builder()
                        .bInvoiceID(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                        .bDetails(cursor.getString(cursor.getColumnIndex(COL_DETAILS)))
                        .bCurrentDate(cursor.getString(cursor.getColumnIndex(COL_DATE)))
                        .build();
                invoiceSet.add(invoice);
            } while (cursor.moveToNext());
        }
        return invoiceSet;
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
