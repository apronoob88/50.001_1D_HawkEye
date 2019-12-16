package com.example.a50001_1d_hawkeye;


/** To create a class DataRank
 * Each object of the class have same attribute namely the name, occupancyRate, number, totalCapacity and key
 * the attributes are exactly same as the information stored in the firebase
 * The class will be for the FirebaseListAdapter to retrieve data from the firebase
 * For instance, getting certain information on the firebase with the same name as the attributes of the class*/

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
