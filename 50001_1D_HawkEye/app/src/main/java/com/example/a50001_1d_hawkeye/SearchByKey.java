package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toolbar;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*The class SearchByKey provides a search function that
* allow the user to search for the location by keying in it's name
* This is done by implementing a search view in a list view */
public class SearchByKey extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    ArrayList<DataItem> updateItem;
    CustomAdapter adapter;
    ArrayList<DataItem> items;
    androidx.appcompat.widget.Toolbar toolbar;
    DatabaseReference reff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialising the references
        setContentView(R.layout.activity_search_by_key);
        listView=(ListView) findViewById(R.id.listView);
        //Created a toolbar to display the search function as the theme selected is
        //the theme that hides the action bar
        toolbar =findViewById(R.id.custom_tool_bar);
        //set the title of the toolbar and promp the user to enter the name of the location
        toolbar.setTitle("Enter the location's name");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        //Creating an ArrayList of DataItems and these dataItems will be displayed on the ListView
        items = new ArrayList<>();
        items.add(new DataItem(R.drawable.mph,"Multi-Purpose Hall"));
        items.add(new DataItem(R.drawable.ish1,"Indoor Sports Hall 1"));
        items.add(new DataItem(R.drawable.ish2,"Indoor Sports Hall 2"));
        items.add(new DataItem(R.drawable.lib1,"Library (level 1)"));
        items.add(new DataItem(R.drawable.lib2,"Library (level 2)"));
        items.add(new DataItem(R.drawable.lib3,"Library (level 3)"));
        items.add(new DataItem(R.drawable.dstar,"dStar Bistro"));
        items.add(new DataItem(R.drawable.crooked_cooks,"Crooked Cooks"));
        items.add(new DataItem(R.drawable.swimming_pool,"Swimming Pool"));
        items.add(new DataItem(R.drawable.study_room,"Study Room"));
        items.add(new DataItem(R.drawable.my_nonnas,"My Nonnas"));
        items.add(new DataItem(R.drawable.gom_gom,"Gom Gom"));
        items.add(new DataItem(R.drawable.gym,"Gym"));
        items.add(new DataItem(R.drawable.campus_centre,"Campus Centre"));
        items.add(new DataItem(R.drawable.canteen,"Canteen"));

        //sort DataItems in the page in alphabetical order
        Collections.sort(items, new Comparator<DataItem>() {
            @Override
            public int compare(DataItem o1, DataItem o2) {
                return o1.getLocation().compareTo(o2.getLocation());
            }
        });

        //The method getData is called once in the onCreate with the input as a empty String
        //This will display all the locations in the school without filtering
        getData("");

        //When an item in the paged is clicked, we will go to the next page "Location" that
        //displays the details of the location
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                //Creating an intent
                final Intent intent = new Intent();
                //pass the name of the location{as a string) to the next page
                intent.putExtra("Location", updateItem.get(position).location);
                //pass the id of the corresponding picture(as an integer) to the next page
                intent.putExtra("picture",updateItem.get(position).id);
                intent.setClass(SearchByKey.this,Location.class);
                // go to Location.java
                startActivity(intent);

            }
        });
    }
    //Each time the user key in a value, the list to be displayed might change
    //updateItem consists of the updated list of items to be displayed on the screen
    public void setUpdateItem(ArrayList<DataItem> updateItem) {
        this.updateItem = updateItem;
    }
    private void getData (String query){

        ArrayList<DataItem> filteredOutput = new ArrayList<>();

        //If something has been keyed in by the user
        if(searchView != null){
            for(DataItem item :items){
                // add the item to the filteredOutput if the location(name) of the item starts with
                // whatever the user had keyed in
                if(item.location.toLowerCase().startsWith(query.toLowerCase())){
                    filteredOutput.add(item);
                }
            }
        }
        // if the user had not input anything, the filteredOutput will just be items
        else{

            filteredOutput=items;
        }
        //set the global variable updateItem as the filteredOutput
        setUpdateItem(filteredOutput);
        // Each time an user input something, a new view is propagated with the item
        adapter = new CustomAdapter(SearchByKey.this,R.layout.itemrow,filteredOutput);
        listView.setAdapter(adapter);

    }

    //Creating a menu for the search function
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        //prompt the user to search for
        searchView.setQueryHint(getString((R.string.Search)));
        searchView.setIconifiedByDefault(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Method getData is called each time the text input of the user changes
                // the text input is passed to getData
                getData(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
