package com.example.a50001_1d_hawkeye;



/* Create a DataItem class to be used in the SearchByKey class
 * Each object of the class will have 2 attributes
 * 1) id : the id of the image to be displayed in the next page when an item in the ListView is clicked
 * 2) location: the Location's name on SearchByKey page*/

public class DataItem {
    int id;
    String location;

    public DataItem(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

}