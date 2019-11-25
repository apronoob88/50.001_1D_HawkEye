package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Building2Lvl1 extends AppCompatActivity {

    private Button CCButton;
    private Button MNButton;
    private Button GGButton;
    private DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building2_lvl1);


        CCButton = findViewById(R.id.ccButton);
        MNButton = findViewById(R.id.myNonnasButton);
        GGButton = findViewById(R.id.gomgomButton);


        //TODO Yutong pls link the firebase info for Crooked Cooks, My Nonnas and Gom Gom
        CCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Crooked Cooks");
                intent.putExtra("picture",R.drawable.crooked_cooks);

                intent.setClass(Building2Lvl1.this,Location.class);
                startActivity(intent);

            }
        });

        MNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "My Nonnas");
                intent.putExtra("picture",R.drawable.my_nonnas);

                intent.setClass(Building2Lvl1.this,Location.class);
                startActivity(intent);

            }
        });

        GGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Gom Gom");
                intent.putExtra("picture",R.drawable.gom_gom);

                intent.setClass(Building2Lvl1.this,Location.class);
                startActivity(intent);

            }
        });
    }
}