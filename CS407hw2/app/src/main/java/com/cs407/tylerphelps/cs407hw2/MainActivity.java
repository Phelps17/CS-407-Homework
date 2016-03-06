package com.cs407.tylerphelps.cs407hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendar;
    private int selectedYear, selectedMonth, selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeCalendar();
    }

    public void initializeCalendar() {
        calendar = (CalendarView) findViewById(R.id.calendar);

        //can add calendar adjustments here

        selectedYear = 0;
        selectedMonth = 0;
        selectedDay = 0;

        calendar.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                if (year == selectedYear && month == selectedMonth && dayOfMonth == selectedDay) {
                    Toast.makeText(getApplicationContext(), "Double Clicked.", Toast.LENGTH_LONG).show();
                }
                else {
                    selectedYear = year;
                    selectedMonth = month;
                    selectedDay = dayOfMonth;

                    Toast.makeText(getApplicationContext(), month + "/" + dayOfMonth + "/" + year, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
