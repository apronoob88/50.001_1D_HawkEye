package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Building1Lvl3 extends AppCompatActivity {

    private ImageButton BackButton;
    private Button LibLvl3Button;
    private DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building1_lvl3);

        BackButton = findViewById(R.id.backButton);
        LibLvl3Button = findViewById(R.id.librarylvl3button);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Building1Lvl3.this, Building1.class);
                startActivity(back);
            }
        });
        //TODO Yutong pls link the firebase info for Lib lvl 3
        LibLvl3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Library (level 3)");
                intent.putExtra("picture",R.drawable.lib3);

                intent.setClass(Building1Lvl3.this,Location.class);
                startActivity(intent);

            }
        });
    }
}
