package com.example.android.quakereport;


/**
 * Created by Fatih YILMAZ on 6.01.2018.
 */

public class Quake {
    private double magnitude;
    private String place;
    private long date;
    private String url;

    public Quake(double magnitude, String label, long date, String url) {
        this.magnitude = magnitude;
        this.place = label;
        this.date = date;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
