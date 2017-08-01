package com.example.weilun.eventcountdown;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wei Lun on 8/1/2017.
 */

public class EventDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "event.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EventContract.EventEntry.TABLE_NAME + " ( " +
                    EventContract.EventEntry._ID + " INTEGER PRIMARY KEY," +
                    EventContract.EventEntry.COLUMN_NAME_TITLE + " Text," +
                    EventContract.EventEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    EventContract.EventEntry.COLUMN_NAME_DATE + " INTEGER," +
                    EventContract.EventEntry.COLUMN_NAME_NOTIFY + " INTEGER),";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + EventContract.EventEntry.TABLE_NAME;

    public EventDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
