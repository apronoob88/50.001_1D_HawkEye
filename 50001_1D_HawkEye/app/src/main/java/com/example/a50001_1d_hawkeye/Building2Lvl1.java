package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Building2Lvl1 extends AppCompatActivity {

    private ImageButton BackButton;
    private Button CCButton;
    private Button MNButton;
    private Button GGButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building2_lvl1);

        BackButton = findViewById(R.id.backButton);
        CCButton = findViewById(R.id.ccButton);
        MNButton = findViewById(R.id.myNonnasButton);
        GGButton = findViewById(R.id.gomgomButton);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Building2Lvl1.this, Building2.class);
                startActivity(back);
            }
        });

        //TODO Yutong pls link the firebase info for Crooked Cooks, My Nonnas and Gom Gom
//        CCButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        MNButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        GGButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}