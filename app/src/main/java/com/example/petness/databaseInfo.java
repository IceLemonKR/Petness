package com.example.petness;

import android.location.Location;

public class databaseInfo {

    public static Location location;
    private double latitude;
    private double longitude;
    private int WalkCnt;

    public double getLatitude(){
        /*
        if(location != null)
        {
            latitude = location.getLatitude();
        }
        */
        return latitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLongitude(){
        /*if(location != null)
        {
            longitude = location.getLongitude();
        }*/

        return longitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public int getWalkCnt(){

        return WalkCnt;
    }

    public void setWalkCnt(int WalCnt){
        this.WalkCnt = WalCnt;
    }
}