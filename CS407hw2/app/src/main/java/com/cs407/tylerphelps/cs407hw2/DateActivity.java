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

    Context context;

    private ArrayList<String> eventList;

    DaoMaster.DevOpenHelper eventDBHelper;
    SQLiteDatabase eventDB;
    DaoMaster daoMaster;
    DaoSession daoSession;
    CalEventDao eventDao;
    List<CalEvent> eventListFromDB;


    private int month, day, year;
    String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        context = this;
        eventList = new ArrayList<>();

        Intent intent = getIntent();

        this.month = intent.getIntExtra("month", 0);
        this.day = intent.getIntExtra("day", 0);
        this.year = intent.getIntExtra("year", 0);
        this.dateString = (month+ 1) + "/" + day + "/" + year;

        initDatabase();

        setTitle(dateString);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.date_fragment_container, DateFragment.newInstance(null, null))
                .addToBackStack(null)
                .commit();
    }

    private void initDatabase() {
        eventDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        eventDB = eventDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(eventDB);

        //Use methods in DaoMaster to create initial database table
        daoMaster.createAllTables(eventDB, true);

        //Use method in DaoMaster to create a database access session
        daoSession = daoMaster.newSession();

        eventDao = daoSession.getCalEventDao();

        if (eventDao.queryBuilder().where(
                CalEventDao.Properties.Display.eq(true)).list() == null)
        {
            closeReopenDatabase();
        }
        eventListFromDB = eventDao.queryBuilder().where(
                CalEventDao.Properties.Display.eq(true)).list();

        if (eventListFromDB != null) {

            for (CalEvent event : eventListFromDB)
            {
                if (event == null)
                {
                    return;
                }
                if (event.getDate().equals(this.dateString)) {
                    eventList.add(event.getName() + " @ " + event.getLocation());
                }
            }
        }
    }

    private void closeDatabase()
    {
        daoSession.clear();
        eventDB.close();
        eventDBHelper.close();
    }

    private void closeReopenDatabase()
    {
        closeDatabase();

        eventDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        eventDB = eventDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(eventDB);

        //Use method in DaoMaster to create a database access session
        daoSession = daoMaster.newSession();

        eventDao = daoSession.getCalEventDao();

    }

    public ArrayList getEventList() {
        return eventList;
    }

    public void newEvent(String name, String location, String startTime, String endTime) {
        Random rand = new Random();

        CalEvent newEvent = new CalEvent(rand.nextLong(), name, location, startTime, endTime, this.dateString, true);

        eventDao.insert(newEvent);
        eventList.add(newEvent.getName() + " @ " + newEvent.getLocation());

        //Close and reopen database to ensure Guest object is saved
        closeReopenDatabase();

        Toast.makeText(this, "New Event Added!", Toast.LENGTH_LONG).show();
    }

    public void deleteEvent(int index) {
        String stringToDelete = eventList.get(index);

        for (CalEvent event : eventListFromDB)
        {
            String curString = event.getName() + " @ " + event.getLocation();
            if (curString.equals(stringToDelete)) {
                //todo DELETE THAT SHIT
                eventDao.delete(event);
                Toast.makeText(this, "Deleting!", Toast.LENGTH_LONG).show();
                eventList.remove(index);
                return;
            }
        }
    }
}
