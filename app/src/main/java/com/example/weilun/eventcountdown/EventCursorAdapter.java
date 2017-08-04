package com.example.weilun.eventcountdown;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by Wei Lun on 8/1/2017.
 */

public class EventCursorAdapter extends CursorAdapter {
    private LayoutInflater inflater;

    public EventCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        String title = cursor.getString(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_TITLE));
        tvTitle.setText(title);

        TextView tvDate = (TextView) view.findViewById(R.id.date);
        long date = cursor.getLong(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_DATE));
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM d, yyyy");
        tvDate.setText(dateFormat.format(date));
    }
}