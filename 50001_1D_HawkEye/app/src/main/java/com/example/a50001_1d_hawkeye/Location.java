package com.example.a50001_1d_hawkeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Location extends AppCompatActivity {
    private TextView name,number, totalCapacity, occupancyRate;
    private ImageView picture;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        name = (TextView)findViewById(R.id.name);
        number = (TextView)findViewById(R.id.number);
        totalCapacity = (TextView)findViewById(R.id.totalCapacity);
        occupancyRate = (TextView)findViewById(R.id.occupancyRate);

        picture=(ImageView)findViewById(R.id.picture);
        //get intent from the previous page, picture set to canteen by default
        //if intent is passed from frevious page, pic will set according o the intent
        picture.setImageResource(getIntent().getIntExtra("picture",R.drawable.canteen));

        reff = FirebaseDatabase.getInstance().getReference().child("Locations");
        final String checkLocation =getIntent().getStringExtra("Location");


        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String numberInner = "number of people: "+dataSnapshot.child(checkLocation).child("number").getValue().toString();
                String totalCapacityInner = "total capacity: "+dataSnapshot.child(checkLocation).child("total capacity").getValue().toString();
                String occupancyRateInner = "total occupancy rate: "+ dataSnapshot.child(checkLocation).child("occupancy rate").getValue().toString()+"%";
                String nameInner = "location: "+dataSnapshot.child(checkLocation).child("name").getValue().toString();
                name.setText(nameInner);
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

