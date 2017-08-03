package com.example.weilun.eventcountdown;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewEventActivity extends AppCompatActivity {
    private Event event;
    private TextView tvTitle, tvDesc, tvDate, tvTime, tvDay, tvHour, tvMinute, tvSecond, tvTimeLeftAgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        if (cursor.moveToNext()) {
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
            tvDay = (TextView) findViewById(R.id.countdown_day);
            tvHour = (TextView) findViewById(R.id.countdown_hour);
            tvMinute = (TextView) findViewById(R.id.countdown_minute);
            tvSecond = (TextView) findViewById(R.id.countdown_second);
            tvTimeLeftAgo = (TextView) findViewById(R.id.time_ago_left);

            tvTitle.setText(event.getTitle());
            tvDesc.setText(event.getDescription());
            tvDate.setText(new SimpleDateFormat("EEEE, MMMM d, yyyy").format(event.getDate()));
            tvTime.setText(new SimpleDateFormat("HH:mm").format(event.getDate()));

            Event.Countdown countdown = event.getCountdown();
            tvDay.setText(Long.toString(countdown.getDays()));
            tvHour.setText(Long.toString(countdown.getHours()));
            tvMinute.setText(Long.toString(countdown.getMinutes()));
            tvSecond.setText(Long.toString(countdown.getSeconds()));

            if (countdown.getDurationInMillis() > 0)
                tvTimeLeftAgo.setText(getString(R.string.left));
            else
                tvTimeLeftAgo.setText(getString(R.string.ago));
        }

    }

    public boolean checkBoolean(int value) {
        return value > 0;
    }
}
