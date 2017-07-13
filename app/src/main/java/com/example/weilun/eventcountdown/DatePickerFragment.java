package com.example.weilun.eventcountdown;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Created by Wei Lun on 7/13/2017.
 */

public class DatePickerFragment extends android.support.v4.app.DialogFragment
        implements DatePickerDialog.OnDateSetListener{

    static Calendar calendar = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(calendar == null)
            calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText etDate = (EditText) getActivity().findViewById((R.id.event_date));
        etDate.setText(String.format(year + "-" + month + "-" + dayOfMonth));
    }
}
