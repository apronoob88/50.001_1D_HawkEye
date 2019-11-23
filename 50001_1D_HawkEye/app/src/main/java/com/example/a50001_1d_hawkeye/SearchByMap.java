package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SearchByMap extends AppCompatActivity {

    private ImageButton Back;
    private Button Building1;
    private Button Building2;
    private Button Building3;
    private Button Blk57;
    private Button Blk61;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_map);

        Building1 = findViewById(R.id.building1);
        Building2 = findViewById(R.id.building2);
        Building3 = findViewById(R.id.building3);
        Blk57 = findViewById(R.id.blk57);
        Blk61 = findViewById(R.id.SportsAndRecreationCentreButton);

        Back = findViewById(R.id.backButton);

        Building1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent building1 = new Intent(SearchByMap.this, Building1.class);
                startActivity(building1);
            }
        });

        Building2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent building2 = new Intent(SearchByMap.this, Building2.class);
                startActivity(building2);
            }
        });

        Building3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent building3 = new Intent(SearchByMap.this, Building3.class);
                startActivity(building3);
            }
        });

        Blk57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent blk57 = new Intent(SearchByMap.this, Blk57.class);
                startActivity(blk57);
            }
        });

        Blk61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent blk61 = new Intent(SearchByMap.this, Blk61.class);
                startActivity(blk61);
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SearchByMap.this, SearchType.class);
                startActivity(back);
            }
        });
    }
}
