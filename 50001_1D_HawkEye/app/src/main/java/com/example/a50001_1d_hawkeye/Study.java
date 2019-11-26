package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class Study extends AppCompatActivity {
    private ListView listView;
    private ArrayList<DataItem> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        listView=(ListView) findViewById(R.id.listView);
        items = new ArrayList<>();
        items.add(new DataItem(R.drawable.crooked_cooks,"Crooked Cooks"));
        items.add(new DataItem(R.drawable.canteen,"Canteen"));
        items.add(new DataItem(R.drawable.gom_gom,"Gom Gom"));

        Collections.sort(items,new CompareItems());
        for(DataItem item: items){
            Log.i("itemLocation",item.getLocation());
        }
    }
}
