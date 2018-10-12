package com.resort.springbootMolvenoLake.models;

public class Time {
    private int hours;
    private int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours %24;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minute) {
        this.minutes = minute%60;
    }
}
