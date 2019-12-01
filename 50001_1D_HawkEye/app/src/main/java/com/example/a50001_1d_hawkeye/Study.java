package com.example.a50001_1d_hawkeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

public class Study extends AppCompatActivity {
    private ListView listView;
    private ArrayList<DataItem> items;
    FirebaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        listView=(ListView) findViewById(R.id.listViewStudy);

        Query query = FirebaseDatabase.getInstance().getReference().child("Dining").orderByChild("occupancyRate");
        FirebaseListOptions<Dining> options = new FirebaseListOptions.Builder<Dining>()
                .setLayout(R.layout.itemrow)
                .setQuery(query, Dining.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                ImageView ivLocation = v.findViewById(R.id.ivLocation);
                TextView tvLocation = v.findViewById(R.id.tvLocation);
                ImageView ivOccupationRate = v.findViewById(R.id.ivOccupancyRate);

                Dining location = (Dining) model;
                String key = location.getKey().toString();

                int resourceId = getResources().getIdentifier(key, "drawable", getPackageName());
                ivLocation.setImageResource(resourceId);

                tvLocation.setText(location.getName().toString());
                if(location.getOccupancyRate()<15){
                    ivOccupationRate.setImageResource(R.drawable.green);
                }
                else if(location.getOccupancyRate()<60){
                    ivOccupationRate.setImageResource(R.drawable.orange);
                }
                else{
                    ivOccupationRate.setImageResource(R.drawable.red);
                }
            }
        };
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Dining");
//                final Intent intent = new Intent();
//
//                intent.putExtra()
//            }
//        });


//        listView.setAdapter(new FirebaseListAdapter<Dining>(this, Dining.class,
//                android.R.layout.simple_list_item_1, occupancyReference) {
//
//            @Override
//            protected void populateView(@NonNull View v, @NonNull Dining model, int position) {
//                ((TextView) v.findViewById(android.R.id.text1)).setText(model.getName());
//            }
//        });
//        adapter = new CustomAdapter(Study.this,R.layout.itemrow,items);
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
