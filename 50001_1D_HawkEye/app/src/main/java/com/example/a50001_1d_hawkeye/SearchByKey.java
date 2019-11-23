package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.SearchView;


import java.util.ArrayList;



import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SearchByKey extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    ArrayList<DataItem> updateItem;
    CustomAdapter adapter;
    ArrayList<DataItem> items;
    ArrayList<String> search;

    DatabaseReference reff;

    public void setUpdateItem(ArrayList<DataItem> updateItem) {
        this.updateItem = updateItem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_key);
        //findViewById is a method that finds the view
        // from the layout resource file that are attached with current Activity.
        listView=(ListView) findViewById(R.id.listView);
        search = new ArrayList<>();

        items = new ArrayList<>();

        items.add(new DataItem(R.drawable.mph,"Multi-Purpose Hall"));
        items.add(new DataItem(R.drawable.ish1,"Indoor Sports Hall 1"));
        items.add(new DataItem(R.drawable.ish2,"Indoor Sports Hall 2"));
        items.add(new DataItem(R.drawable.lib1,"Library (level 1)"));
        items.add(new DataItem(R.drawable.lib2,"Library (level 2)"));
        items.add(new DataItem(R.drawable.lib3,"Library (level 3)"));
        items.add(new DataItem(R.drawable.dstar,"dStar Bistro"));
        items.add(new DataItem(R.drawable.crooked_cooks,"Crooked Cooks"));
        items.add(new DataItem(R.drawable.my_nonnas,"My Nonnas"));
        items.add(new DataItem(R.drawable.gom_gom,"Gom Gom"));
        items.add(new DataItem(R.drawable.canpus_centre,"Campus Centre"));
        items.add(new DataItem(R.drawable.canteen,"Canteen"));

        getData("");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                //final String checkLocation=updateItem.get(position).location;
                final Intent intent = new Intent();

                intent.putExtra("Location", updateItem.get(position).location);
                intent.putExtra("picture",updateItem.get(position).id);

                intent.setClass(SearchByKey.this,Location.class);
                startActivity(intent);

            }
        });
    }
    private void getData (String query){
        ArrayList<DataItem> output = new ArrayList<>();
        ArrayList<DataItem> filteredOutput = new ArrayList<>();

        for (int i=0; i<items.size();i++){
            output.add(items.get(i));
        }
        if(searchView != null){
            for(DataItem item :output){
                if(item.location.toLowerCase().startsWith(query.toLowerCase())){
                    filteredOutput.add(item);
                }
            }
        }
        else{
            filteredOutput=output;
        }
        setUpdateItem(filteredOutput);
        adapter = new CustomAdapter(SearchByKey.this,R.layout.itemrow,filteredOutput);
        listView.setAdapter(adapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        //useless??
        searchView.setQueryHint(getString((R.string.Search)));
        //useless??
        searchView.setIconifiedByDefault(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getData(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
