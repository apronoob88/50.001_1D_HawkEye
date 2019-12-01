package com.example.a50001_1d_hawkeye;

public class DataRank {
    private String name;
    private int occupancyRate;
    private int number;
    private int totalCapacity;
    private String key;
    DataRank(){

    }

    public DataRank(String name, int occupancyRate, int number, int totalCapacity) {
        this.name = name;
        this.occupancyRate = occupancyRate;
        this.number = number;
        this.totalCapacity = totalCapacity;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public int getOccupancyRate() {
        return occupancyRate;
    }

    public int getNumber() {
        return number;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupancyRate(int occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

}
