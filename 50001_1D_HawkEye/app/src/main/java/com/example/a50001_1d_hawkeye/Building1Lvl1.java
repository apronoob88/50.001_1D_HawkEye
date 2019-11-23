package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Building1Lvl1 extends AppCompatActivity {

    private ImageButton BackButton;
    private Button LibLvl1Button;
    private Button dStarButton;

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
//        LibLvl1Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        dStarButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
