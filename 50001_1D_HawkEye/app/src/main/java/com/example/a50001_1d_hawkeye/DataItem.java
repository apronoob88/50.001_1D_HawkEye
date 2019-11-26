package com.example.a50001_1d_hawkeye;

public class DataItem {
    int id;
    String location;
    int occupancyRate;
    public DataItem(int id, String location){
        this.id=id;
        this.location=location;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setOccupancyRate(int occupancyRate) {
        this.occupancyRate = occupancyRate;
    }
}