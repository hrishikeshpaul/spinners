package com.wmps.paul_mac.question2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText changeDate;
    Spinner destination;
    Spinner source;
    private int year;
    private int month;
    private int day;
    static final int TIME_DIALOG_ID = 111;
    public EditText changeTime;
    private int hour;
    private int minute;
    static final int DATE_PICKER_ID = 1111;

    Button clear;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        changeDate = (EditText) findViewById(R.id.changeDate);
        destination = (Spinner) findViewById(R.id.sp1);
        source = (Spinner) findViewById(R.id.sp2);
        clear = (Button) findViewById(R.id.clear);
        submit = (Button) findViewById(R.id.submit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.places, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        source.setAdapter(adapter);
        destination.setAdapter(adapter);

        // Get current date by calender
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        addButtonClickListener();

        // Button listener to show date picker dialog
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On button click show datepicker dialog
                showDialog(DATE_PICKER_ID);
            }
        });
        
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = c.get(Calendar.YEAR);
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                changeTime.setText(new StringBuilder().append(utilTime(hour))
                        .append(":").append(utilTime(minute)));
                changeDate.setText(new StringBuilder().append(day + 1)
                        .append("-").append(month).append("-").append(year)
                        .append(" "));
                destination.setSelection(0);
                source.setSelection(0);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AvailableTrains.class);
                Bundle b = new Bundle();

                if(source.getSelectedItem().equals(destination.getSelectedItem())) {
                    Toast.makeText(getApplicationContext(),"Source and destination should be different",Toast.LENGTH_LONG).show();
                }
                else{
                    b.putString("source", String.valueOf(source.getSelectedItem()));
                    b.putString("destination", String.valueOf(destination.getSelectedItem()));
                    b.putString("hour", String.valueOf(hour));
                    b.putString("minute", String.valueOf(minute));
                    b.putString("year", String.valueOf(year));
                    b.putString("month", String.valueOf(month));
                    b.putString("day", String.valueOf(day));
                    i.putExtras(b);
                    startActivity(i);
                    setContentView(R.layout.flight_main);
                }


            }
        });


    }


    public void addButtonClickListener() {
        changeTime = (EditText) findViewById(R.id.changeTime);
        changeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, year, month, day);

            case TIME_DIALOG_ID:

                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener, hour, minute,
                        false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // Show selected date
            changeDate.setText(new StringBuilder().append(day + 1)
                    .append("-").append(month).append("-").append(year)
                    .append(" "));

        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            // TODO Auto-generated method stub
            hour = hourOfDay;
            minute = minutes;

            // set current time into output textview
            changeTime.setText(new StringBuilder().append(utilTime(hour))
                    .append(":").append(utilTime(minute)));
        }

    };

    private static String utilTime(int value) {

        if (value < 10)
            return "0" + String.valueOf(value);
        else
            return String.valueOf(value);
    }

}
