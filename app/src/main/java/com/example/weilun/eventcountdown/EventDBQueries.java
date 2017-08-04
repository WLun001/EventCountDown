package com.example.weilun.eventcountdown;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Wei Lun on 8/1/2017.
 */

public class EventDBQueries {
    private EventDBHelper helper;

    public EventDBQueries(EventDBHelper helper) {
        this.helper = helper;
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy
            , String having, String orderBy) {
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(EventContract.EventEntry.TABLE_NAME, columns, selection, selectionArgs, groupBy
                , having, orderBy);
    }

    public long insert(Event event) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventContract.EventEntry.COLUMN_NAME_TITLE, event.getTitle());
        values.put(EventContract.EventEntry.COLUMN_NAME_DESCRIPTION, event.getDescription());
        values.put(EventContract.EventEntry.COLUMN_NAME_DATE, event.getDateAsCalendar().getTimeInMillis());
        values.put(EventContract.EventEntry.COLUMN_NAME_NOTIFY, event.isNotify());

        long id = db.insert(EventContract.EventEntry.TABLE_NAME, null, values);
        event.setId(id);
        return id;
    }

    public int update(Event event) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EventContract.EventEntry.COLUMN_NAME_TITLE, event.getTitle());
        values.put(EventContract.EventEntry.COLUMN_NAME_DESCRIPTION, event.getDescription());
        values.put(EventContract.EventEntry.COLUMN_NAME_DATE, event.getDateAsCalendar().getTimeInMillis());
        values.put(EventContract.EventEntry.COLUMN_NAME_NOTIFY, event.isNotify());

        String selection = EventContract.EventEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(event.getId())};

        return db.update(EventContract.EventEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public void deleteOne(Long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String selection = EventContract.EventEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        db.delete(EventContract.EventEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void deleteAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(EventContract.EventEntry.TABLE_NAME, null, null);
    }
}
