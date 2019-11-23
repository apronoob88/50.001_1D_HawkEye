package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Building3 extends AppCompatActivity {

    private Button Level1;
    private Button Level2;
    private ImageButton Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building3);

        Back = findViewById(R.id.backButton);
        Level1 = findViewById(R.id.b3l1);
        Level2 = findViewById(R.id.b3l2);

        Level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B3L1 = new Intent(Building3.this, Building3Lvl1.class);
                startActivity(B3L1);
            }
        });

        Level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B3L2 = new Intent(Building3.this, Building3Lvl2.class);
                startActivity(B3L2);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Building3.this, SearchByMap.class);
                startActivity(back);
            }
        });


    }
}
