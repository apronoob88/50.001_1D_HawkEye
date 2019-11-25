package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Building1 extends AppCompatActivity {

    private Button Level1;
    private Button Level2;
    private Button Level3;
    private ImageButton Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building1);


        Level1 = findViewById(R.id.b1l1);
        Level2 = findViewById(R.id.b1l2);
        Level3 = findViewById(R.id.b1l3);

        Level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B1L1 = new Intent(Building1.this, Building1Lvl1.class);
                startActivity(B1L1);
            }
        });

        Level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B1L2 = new Intent(Building1.this, Building1Lvl2.class);
                startActivity(B1L2);
            }
        });

        Level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B1L3 = new Intent(Building1.this, Building1Lvl3.class);
                startActivity(B1L3);
            }
        });

    }
}
