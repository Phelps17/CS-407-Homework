package com.cs407.tylerphelps.cs407hw2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.widget.Toast;

public class DateActivity extends AppCompatActivity {

    //TODO access database of events
    Context context;

    /*
    DaoMaster.DevOpenHelper calendarDBHelper;
    SQLiteDatabase calendarDB;
    DaoMaster daoMaster;
    DaoSession daoSession;

    EventDao eventDao;*/
    List<Event> eventsOnDate;

    private long dateId;
    private int month, day, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Intent intent = getIntent();

        this.month = intent.getIntExtra("month", 0);
        this.day = intent.getIntExtra("day", 0);
        this.year = intent.getIntExtra("year", 0);
        this.dateId = intent.getLongExtra("dateId", 0);

        context = this;
        //initDatabase();
        this.eventsOnDate = new ArrayList<>();

        setTitle((month+ 1) + "/" + day + "/" + year);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.date_fragment_container, DateFragment.newInstance(null, null))
                .addToBackStack(null)
                .commit();
    }

    /*
    private void initDatabase() {
        calendarDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        calendarDB = calendarDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(calendarDB);

        //Use methods in DaoMaster to create initial database table
        daoMaster.createAllTables(calendarDB, true);

        //Use method in DaoMaster to create a database access session
        daoSession = daoMaster.newSession();

        eventDao = daoSession.getEventDao();

        if (eventDao.queryBuilder().where(
                EventDao.Properties.Display.eq(true)).list() == null)
        {
            closeReopenDatabase();
        }

        eventsOnDate = eventDao.queryBuilder().where(EventDao.Properties.Display.eq(true)).list();
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

        eventDao = daoSession.getEventDao();

    }*/

    public ArrayList getEventList() {
        ArrayList<String> eventStrings = new ArrayList();

        for (Event event : eventsOnDate) {
            String eventString = event.getName();
            eventStrings.add(eventString);
        }

        return eventStrings;
    }

    public void newEvent(String name, String location, String startTime, String endTime) {
        Random rand = new Random();

        Event newEvent = new Event((Long) rand.nextLong(), name, location, startTime, endTime, (Long) this.dateId, true);

        /*
        eventDao.insert(newEvent);

        //Close and reopen database to ensure Guest object is saved
        closeReopenDatabase();

        eventsOnDate = eventDao.queryBuilder().where(EventDao.Properties.Display.eq(true)).list();*/

        Toast.makeText(this, "New Event Added!", Toast.LENGTH_LONG).show();
        eventsOnDate.add(newEvent);
    }

}
