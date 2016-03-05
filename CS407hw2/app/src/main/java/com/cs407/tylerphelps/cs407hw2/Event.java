package com.cs407.tylerphelps.cs407hw2;

/**
 * Created by TylerPhelps on 3/4/16.
 */
public class Event {
    private int startTime, endTime;
    private String name, location;

    public Event(String name, String location, int startTime, int endTime) {
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.startTime;
    }
}
