package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Building3Lvl1 extends AppCompatActivity {

    private ImageButton BackButton;
    private Button CampusCentreButton;
    private DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building3_lvl1);

        BackButton = findViewById(R.id.backButton);
        CampusCentreButton = findViewById(R.id.campuscentreButton);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Building3Lvl1.this, Building3.class);
                startActivity(back);
            }
        });
        //TODO Yutong pls link the firebase info for Campus Centre
        CampusCentreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Campus Centre");
                intent.putExtra("picture",R.drawable.canpus_centre);

                intent.setClass(Building3Lvl1.this,Location.class);
                startActivity(intent);

            }
        });
    }
}
