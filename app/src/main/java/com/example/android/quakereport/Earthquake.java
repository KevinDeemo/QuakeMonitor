package com.example.android.quakereport;

public class Earthquake {

    private Double mag;

    private String location;

    private long date;

    private String url;

    public Earthquake(Double mag, String location, Long date, String url){
        this.mag = mag;
        this.location = location;
        this.date = date;
        this.url = url;
    }

    public Double getMag(){
        return mag;
    }

    public String getLocation(){
        return location;
    }

    public long getDate(){
        return date;
    }

    public String getUrl(){
        return url;
    }
}
