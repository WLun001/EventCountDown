package com.example.weilun.eventcountdown;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Wei Lun on 7/14/2017.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    static Calendar calendar;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, false);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, hourOfDay, minute, Calendar.SECOND);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String date = dateFormat.format((calendar.getTime()));
        EditText etTime = (EditText) getActivity().findViewById(R.id.event_time);
        etTime.setText(date);
    }
}
