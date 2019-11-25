package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Building1Lvl2 extends AppCompatActivity {

    private ImageButton BackButton;
    private Button LibLvl2Button;
    private DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building1_lvl2);


        LibLvl2Button = findViewById(R.id.librarylvl2button);


        //TODO Yutong pls link the firebase info for Lib lvl 2
        LibLvl2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Library (level 2)");
                intent.putExtra("picture",R.drawable.lib2);

                intent.setClass(Building1Lvl2.this,Location.class);
                startActivity(intent);

            }
        });
    }
}
