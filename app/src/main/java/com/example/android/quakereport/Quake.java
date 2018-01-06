package com.example.android.quakereport;


/**
 * Created by Fatih YILMAZ on 6.01.2018.
 */

public class Quake {
    private String magnitude;
    private String label;
    private String date;


    public Quake(String magnitude, String label, String date) {
        this.magnitude = magnitude;
        this.label = label;
        this.date = date;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
