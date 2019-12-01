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
        pic = findViewById(R.id.picture);
        String location = getIntent().getStringExtra("Name");
        name.setText(location);

        final String locationName = location;
        final String category = getIntent().getStringExtra("category");
        reff = FirebaseDatabase.getInstance().getReference().child(category);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child(locationName).child("key").getValue().toString();
                int resourceId = getResources().getIdentifier(name, "drawable", getPackageName());
                String totalCapacityInner = "total capacity: "+dataSnapshot.child(locationName).child("totalCapacity").getValue().toString();
                String occupancyRateInner = "total occupancy rate: "+ dataSnapshot.child(locationName).child("occupancyRate").getValue().toString()+"%";
                String numberInner = "number of people: "+dataSnapshot.child(locationName).child("number").getValue().toString();
                pic.setImageResource(resourceId);
                number.setText(numberInner);
                totalCapacity.setText(totalCapacityInner);
                occupancyRate.setText(occupancyRateInner);
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
