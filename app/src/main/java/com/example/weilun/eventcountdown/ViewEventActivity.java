package com.example.weilun.eventcountdown;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ViewEventActivity extends AppCompatActivity {
    private Event event;
    private TextView tvTitle, tvDesc, tvDate, tvTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        long id = intent.getLongExtra(MainActivity.EXTRA_ID, 0);

        EventDBQueries dbQueries = new EventDBQueries(new EventDBHelper(getApplicationContext()));

        final String[] columns = {
                EventContract.EventEntry._ID,
                EventContract.EventEntry.COLUMN_NAME_TITLE,
                EventContract.EventEntry.COLUMN_NAME_DESCRIPTION,
                EventContract.EventEntry.COLUMN_NAME_DATE,
                EventContract.EventEntry.COLUMN_NAME_NOTIFY
        };

        String selection = EventContract.EventEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        Cursor cursor = dbQueries.query(columns, selection, selectionArgs, null, null, null);
        if(cursor.moveToNext()){
            event = new Event(
                    cursor.getLong(cursor.getColumnIndex(EventContract.EventEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_TITLE)),
                    cursor.getString(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_DESCRIPTION)),
                    new Date(cursor.getLong(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_DATE))),
                    checkBoolean(cursor.getInt(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME_NOTIFY)))
            );

            tvTitle = (TextView) findViewById(R.id.view_title);
            tvDesc = (TextView) findViewById(R.id.view_description);
            tvDate = (TextView) findViewById(R.id.view_date);
            tvTime = (TextView) findViewById(R.id.view_time);

            tvTitle.setText(event.getTitle());
            tvDesc.setText(event.getDescription());
            tvDate.setText(new SimpleDateFormat ("EEEE, MMMM d, yyyy").format(event.getDate()));
            tvTime.setText(new SimpleDateFormat ("HH:mm").format(event.getDate()));


    }

}

public boolean checkBoolean(int value){
    return value > 0;
}
}
