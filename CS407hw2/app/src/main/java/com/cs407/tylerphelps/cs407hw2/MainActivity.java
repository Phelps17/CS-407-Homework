package com.cs407.tylerphelps.cs407hw2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendar;

    Context context;

    DaoMaster.DevOpenHelper calendarDBHelper;
    SQLiteDatabase calendarDB;
    DaoMaster daoMaster;
    DaoSession daoSession;
    CalDateDao dateDao;
    List<CalDate> datesInDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Calendar");

        context = this;

        initDatabase();
        initializeCalendar();
    }

    private void initDatabase() {
        calendarDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        calendarDB = calendarDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(calendarDB);

        //Use methods in DaoMaster to create initial database table
        daoMaster.createAllTables(calendarDB, true);

        //Use method in DaoMaster to create a database access session
        daoSession = daoMaster.newSession();

        dateDao = daoSession.getCalDateDao();

        if (dateDao.queryBuilder().where(
                CalDateDao.Properties.Display.eq(true)).list() == null)
        {
            closeReopenDatabase();
        }
        datesInDB = dateDao.queryBuilder().where(
                CalDateDao.Properties.Display.eq(true)).list();
    }

    public void initializeCalendar() {
        calendar = (CalendarView) findViewById(R.id.calendar);

        //can add calendar adjustments here

        calendar.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                CalDate selectedDate = containsDate(month, dayOfMonth, year);

                if (selectedDate != null) {
                    Toast.makeText(getApplicationContext(), "In DB", Toast.LENGTH_LONG).show();

                    Intent date = new Intent(context, DateActivity.class);

                    date.putExtra("month", month);
                    date.putExtra("day", dayOfMonth);
                    date.putExtra("year", year);

                    startActivity(date);
                }
                else {
                    newDate(month, dayOfMonth, year);
                    Toast.makeText(getApplicationContext(), "Added to DB", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void newDate(int month, int day, int year)
    {
        //Generate random Id for Guest object to place in database
        Random rand = new Random();

        CalDate newDate = new CalDate(rand.nextLong(), (Integer) month, (Integer) day, (Integer) year, true);

        dateDao.insert(newDate);

        //Close and reopen database to ensure Guest object is saved
        closeReopenDatabase();

        datesInDB = dateDao.queryBuilder().where(
                CalDateDao.Properties.Display.eq(true)).list();
    }

    private CalDate containsDate(int month, int day, int year) {
        for (CalDate itrDate : datesInDB) {
            if (itrDate.getMonth() == month && itrDate.getDay() == day && itrDate.getYear() == year) {
                return itrDate;
            }
        }

        return null;
    }

    private void closeDatabase()
    {
        daoSession.clear();
        calendarDB.close();
        calendarDBHelper.close();
    }

    private void closeReopenDatabase()
    {
        closeDatabase();

        calendarDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        calendarDB = calendarDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(calendarDB);

        //Use method in DaoMaster to create a database access session
        daoSession = daoMaster.newSession();

        dateDao = daoSession.getCalDateDao();

    }
}
