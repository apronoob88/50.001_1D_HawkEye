package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Building3Lvl1 extends AppCompatActivity {

    private ImageButton BackButton;
    private Button CampusCentreButton;

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
//        CampusCentreButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent infopage = new Intent(Building3Lvl1.this, Infopage.class);
//                infopage.putExtra("Location","Campus Centre");
//            }
//        });
    }
}
