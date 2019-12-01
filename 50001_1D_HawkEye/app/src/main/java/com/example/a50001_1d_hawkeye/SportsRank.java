package com.example.a50001_1d_hawkeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class SportsRank extends AppCompatActivity {
    private ListView listView;
    private ArrayList<DataItem> items;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_rank);
        listView=(ListView) findViewById(R.id.listViewSports);

        Query query = FirebaseDatabase.getInstance().getReference().child("Sports").orderByChild("occupancyRate");
        FirebaseListOptions<DataRank> options = new FirebaseListOptions.Builder<DataRank>()
                .setLayout(R.layout.itemrow)
                .setQuery(query, DataRank.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                ImageView ivLocation = v.findViewById(R.id.ivLocation);
                TextView tvLocation = v.findViewById(R.id.tvLocation);
                ImageView ivOccupationRate = v.findViewById(R.id.ivOccupancyRate);

                DataRank location = (DataRank) model;
                String key = location.getKey().toString();
                Log.i("jemy", key);

                int resourceId = getResources().getIdentifier(key, "drawable", getPackageName());
                ivLocation.setImageResource(resourceId);

                tvLocation.setText(location.getName().toString());
                Log.i("jemy", location.getName().toString());
                if(location.getOccupancyRate()<15){
                    ivOccupationRate.setImageResource(R.drawable.green);
                }
                else if(location.getOccupancyRate()<60){
                    ivOccupationRate.setImageResource(R.drawable.orange);
                }
                else{
                    ivOccupationRate.setImageResource(R.drawable.red);
                }
                final String locationClicked = getRef(position).getKey();
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent moreDetailed = new Intent(SportsRank.this, SearchByRankDetails.class);
                        moreDetailed.putExtra("Name",locationClicked);
                        moreDetailed.putExtra("category","Sports");
                        startActivity(moreDetailed);//Start the new activity

                    }
                });
            }
        };
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("DataRank");
//                final Intent intent = new Intent();
//
//                intent.putExtra()
//            }
//        });


//        listView.setAdapter(new FirebaseListAdapter<DataRank>(this, DataRank.class,
//                android.R.layout.simple_list_item_1, occupancyReference) {
//
//            @Override
//            protected void populateView(@NonNull View v, @NonNull DataRank model, int position) {
//                ((TextView) v.findViewById(android.R.id.text1)).setText(model.getName());
//            }
//        });
//        adapter = new CustomAdapter(DiningRank.this,R.layout.itemrow,items);
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