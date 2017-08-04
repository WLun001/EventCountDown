package com.example.weilun.eventcountdown;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class EditEventActivity extends AppCompatActivity {
    private EditText etTitle, etDesc, etDate, etTime;
    private Switch aSwitch;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        event = (Event) intent.getSerializableExtra(ViewEventActivity.EXTRA_EVENT);

        etTitle = (EditText) findViewById(R.id.event_title);
        etDesc = (EditText) findViewById(R.id.event_desc);
        etTime = (EditText) findViewById(R.id.event_time);
        etDate = (EditText) findViewById(R.id.event_date);
        aSwitch = (Switch) findViewById(R.id.show_noti);

        etTitle.setText(event.getTitle());
        etDesc.setText(event.getDescription());
        etDate.setText(new SimpleDateFormat("EEEE, MMMM d, yyyy").format(event.getDate()));
        etTime.setText(new SimpleDateFormat("hh:mm a").format(event.getDate()));
        aSwitch.setChecked(event.isNotify());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    event.setTitle(etTitle.getText().toString());
                    event.setDescription(etDesc.getText().toString());
                    event.setDate(new SimpleDateFormat("EEEE, MMMM d, yyyy HH:mm ", Locale.ENGLISH).parse(
                            etDate.getText().toString() + " " + etTime.getText().toString()
                    ));
                    event.setNotify(aSwitch.isChecked());

                    EventDBQueries dbQueries = new EventDBQueries(new EventDBHelper(getApplicationContext()));
                    if (dbQueries.update(event) != 0) {
                        Toast.makeText(EditEventActivity.this, getString(R.string.update_success), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (ParseException e) {
                }
            }
        });
    }

    public void showDatePickerDialog(View view) {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View view) {
        DialogFragment fragment = new TimePickerFragment();
        fragment.show(getSupportFragmentManager(), "timePicker");
    }
}
