package com.example.weilun.eventcountdown;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Wei Lun on 8/1/2017.
 */

public class EventDBQueries {
    private EventDBHelper helper;

    public EventDBQueries(EventDBHelper helper){
        this.helper = helper;
    }
    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy
    , String having, String orderBy){
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(EventContract.EventEntry.TABLE_NAME, columns, selection, selectionArgs, groupBy
        , having, orderBy);
    }

    public long insert(Event event){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventContract.EventEntry.COLUMN_NAME_TITLE, event.getTitle());
        values.put(EventContract.EventEntry.COLUMN_NAME_DESCRIPTION, event.getDescription());
        values.put(EventContract.EventEntry.COLUMN_NAME_DATE,event.getDateAsCalendar().getTimeInMillis());
        values.put(EventContract.EventEntry.COLUMN_NAME_NOTIFY, event.isNotify());

        long id = db.insert(EventContract.EventEntry.TABLE_NAME, null, values);
        event.setId(id);
        return id;
    }
}