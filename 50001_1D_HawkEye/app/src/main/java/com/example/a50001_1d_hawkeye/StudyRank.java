package com.example.a50001_1d_hawkeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class StudyRank extends AppCompatActivity {
    private ListView listView;
    private ArrayList<DataItem> items;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_rank);
        listView=(ListView) findViewById(R.id.listViewStudy);

        Query query = FirebaseDatabase.getInstance().getReference().child("Study").orderByChild("occupancyRate");
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

                int resourceId = getResources().getIdentifier(key, "drawable", getPackageName());
                ivLocation.setImageResource(resourceId);

                tvLocation.setText(location.getName().toString());
                if(location.getOccupancyRate()<20){
                    ivOccupationRate.setImageResource(R.drawable.green);
                }
                else if(location.getOccupancyRate()<40){
                    ivOccupationRate.setImageResource(R.drawable.yellow);
                }
                else if(location.getOccupancyRate()<70){
                    ivOccupationRate.setImageResource(R.drawable.orange);
                }
                else{
                    ivOccupationRate.setImageResource(R.drawable.red);
                }
                final String locationClicked = getRef(position).getKey();
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent moreDetailed = new Intent(StudyRank.this, SearchByRankDetails.class);
                        moreDetailed.putExtra("Name",locationClicked);
                        moreDetailed.putExtra("category","Study");
                        startActivity(moreDetailed);//Start the new activity

                    }
                });
            }
        };
        listView.setAdapter(adapter);

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