package com.example.a50001_1d_hawkeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchByRankDetails extends AppCompatActivity {
    private TextView name;
    private TextView number;
    private TextView totalCapacity;
    private TextView occupancyRate;
    private TextView nameContent, numberContent, totalCapacityContent, occupancyRateContent;
    private ImageView pic;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_rank_details);
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        totalCapacity = findViewById(R.id.totalCapacity);
        occupancyRate = findViewById(R.id.occupancyRate);
        nameContent = findViewById(R.id.nameContent);
        numberContent = findViewById(R.id.numberContent);
        totalCapacityContent = findViewById(R.id.totalCapacityContent);
        occupancyRateContent = findViewById(R.id.occupancyRateContent);
        pic = findViewById(R.id.picture);

        // Obtain the string intent of which location is clicked from the previous page
        String location = getIntent().getStringExtra("Name");
        name.setText(location);

        //The location being clicked in the previous page
        // the details of this specific location will be retrieved from the firebase
        final String locationName = location;

        // Obtain the string intent of which category to look for the location on the firebase
        final String category = getIntent().getStringExtra("category");
        reff = FirebaseDatabase.getInstance().getReference().child(category);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Get the String Key stored the fire base
                String name = dataSnapshot.child(locationName).child("key").getValue().toString();
                // Convert the String key obtained from the firebase to an integer resource ID
                int resourceId = getResources().getIdentifier(name, "drawable", getPackageName());
                String totalCapacityInner = "Total Capacity: ";
                String occupancyRateInner = "Total Occupancy Rate: ";
                String numberInner = "Number of people: ";
                String numberContent1 = dataSnapshot.child(locationName).child("number").getValue().toString();
                String totalCapacityContent1 = dataSnapshot.child(locationName).child("totalCapacity").getValue().toString();
                float occupancyRateFloat = Math.round((Float.parseFloat(numberContent1) / Float.parseFloat(totalCapacityContent1)) * 100);
                // Set the ImageView with image id corresponding the the key retrieved from the firebase
                pic.setImageResource(resourceId);
                number.setText(numberInner);
                totalCapacity.setText(totalCapacityInner);
                occupancyRate.setText(occupancyRateInner);
                numberContent.setText(numberContent1);
                totalCapacityContent.setText(totalCapacityContent1);
                occupancyRateContent.setText(Float.toString(occupancyRateFloat) + "%");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
