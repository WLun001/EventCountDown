package com.example.weilun.eventcountdown;

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
import java.util.Date;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {
    private EditText etTitle, etDesc, etTime, etDate;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //for display navigation up

        etTitle = (EditText) findViewById(R.id.event_title);
        etDesc = (EditText) findViewById(R.id.event_desc);
        etTime = (EditText) findViewById(R.id.event_time);
        etDate = (EditText) findViewById(R.id.event_date);
        aSwitch = (Switch) findViewById(R.id.show_noti);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String title = etTitle.getText().toString();
                    String desc = etDesc.getText().toString();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy HH:mm", Locale.ENGLISH);
                    Date date = dateFormat.parse(etDate.getText().toString() + " " + etTime.getText().toString());
                    Boolean isChecked = aSwitch.isChecked();

                    EventDBQueries dbQueries = new EventDBQueries(new EventDBHelper(getApplicationContext()));
                    Event event = new Event(0, title, desc, date, isChecked);
                    if(dbQueries.insert(event) != 0){
                        Toast.makeText(AddEventActivity.this, "insert success!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (ParseException e) {
                    Toast.makeText(AddEventActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
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
