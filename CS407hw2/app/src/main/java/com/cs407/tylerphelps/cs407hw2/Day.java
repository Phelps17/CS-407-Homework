package com.cs407.tylerphelps.cs407hw2;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by TylerPhelps on 3/4/16.
 */

public class Day {
    private String month;
    private int day, year;
    private ArrayList<Event> eventsOnDate;

    public Day(String month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.eventsOnDate = new ArrayList<>();
    }

    public String getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getYear() {
        return this.year;
    }

    public boolean addEvent(Event event) {
        return this.eventsOnDate.add(event);
    }

    public ArrayList getEvents() {
        return this.eventsOnDate;
    }

    public boolean removeEvent(Event event) {
        return this.eventsOnDate.remove(event);
    }
}
