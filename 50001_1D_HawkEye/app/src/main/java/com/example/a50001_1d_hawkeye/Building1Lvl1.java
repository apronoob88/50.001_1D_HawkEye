package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Building1Lvl1 extends AppCompatActivity {

    private ImageButton BackButton;
    private Button LibLvl1Button;
    private Button dStarButton;
    private DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building1_lvl1);

        BackButton = findViewById(R.id.backButton);
        LibLvl1Button = findViewById(R.id.librarylvl1button);
        dStarButton = findViewById(R.id.dstarButton);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Building1Lvl1.this, Building1.class);
                startActivity(back);
            }
        });
        //TODO Yutong pls link the firebase info for Lib lvl 1 and dStar Bistro
        LibLvl1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Library (level 1)");
                intent.putExtra("picture",R.drawable.lib1);

                intent.setClass(Building1Lvl1.this,Location.class);
                startActivity(intent);

            }
        });

        dStarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "dStar Bistro");
                intent.putExtra("picture",R.drawable.dstar);

                intent.setClass(Building1Lvl1.this,Location.class);
                startActivity(intent);
            }
        });
    }
}
