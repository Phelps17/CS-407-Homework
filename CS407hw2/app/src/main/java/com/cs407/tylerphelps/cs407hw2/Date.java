package com.cs407.tylerphelps.cs407hw2;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DATE.
 */
public class Date {

    private Long id;
    private String month;
    private Integer day;
    private Integer year;
    private Boolean display;

    public Date() {
    }

    public Date(Long id) {
        this.id = id;
    }

    public Date(Long id, String month, Integer day, Integer year, Boolean display) {
        this.id = id;
        this.month = month;
        this.day = day;
        this.year = year;
        this.display = display;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

}
