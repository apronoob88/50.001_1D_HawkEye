package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Blk57Lvl2 extends AppCompatActivity {

    private ImageButton BackButton;
    private Button MPHButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blk57_lvl2);

        BackButton = findViewById(R.id.backButton);
        MPHButton = findViewById(R.id.mphButton);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Blk57Lvl2.this, Blk57.class);
            }
        });
        //TODO Yutong pls link the firebase info for MPH
//        MPHButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
