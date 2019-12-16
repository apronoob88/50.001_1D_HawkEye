package com.example.a50001_1d_hawkeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class BigEventsRank extends AppCompatActivity {
    private ListView listView;
    private ArrayList<DataItem> items;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_events);
        listView=(ListView) findViewById(R.id.listViewBigEvents);

        //Order the locations displayed under the Study category based on their occupancyRate
        Query query = FirebaseDatabase.getInstance().getReference().child("Big Events").orderByChild("occupancyRate");
        FirebaseListOptions<DataRank> options = new FirebaseListOptions.Builder<DataRank>()
                .setLayout(R.layout.itemrow)
                .setQuery(query, DataRank.class)
                .build();

        // populating the view using FirebaseListAdapter
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                ImageView ivLocation = v.findViewById(R.id.ivLocation);
                TextView tvLocation = v.findViewById(R.id.tvLocation);
                ImageView ivOccupationRate = v.findViewById(R.id.ivOccupancyRate);

                DataRank location = (DataRank) model;

                // Retrieve the keys(string with the same name as images in the drawable file) stored under each location on the firebase
                String key = location.getKey().toString();

                // Convert the key retrieved into integer resource id
                int resourceId = getResources().getIdentifier(key, "drawable", getPackageName());
                //Set the corresponding image beside each location displayed
                ivLocation.setImageResource(resourceId);

                // Set the TextView as the name of the location
                tvLocation.setText(location.getName().toString());

                // Set a different coloured images to indicate the emptiness of the place
                // based on the occupancyRate
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
                        Intent moreDetailed = new Intent(BigEventsRank.this, SearchByRankDetails.class);
                        // put an intent of which location is being clicked based
                        // on the index of the location on the page
                        moreDetailed.putExtra("Name",locationClicked);
                        // Pass the String intent "Sports", so that the nxt page can
                        // retrieve data under the node "Study" from the firebase
                        moreDetailed.putExtra("category","Big Events");
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