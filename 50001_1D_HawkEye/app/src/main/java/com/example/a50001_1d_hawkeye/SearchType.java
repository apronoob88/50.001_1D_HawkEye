package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SearchType extends AppCompatActivity {

    private TextView SearchBy;
    private Button Keyword;
    private Button Map;
    private Button OccupancyRanking;
    private ImageButton Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_type);

        Keyword = findViewById(R.id.buttonKeyword);
        Map = findViewById(R.id.buttonMap);
        OccupancyRanking = findViewById(R.id.buttonOccupancyRanking);
        Back = findViewById(R.id.backButton);

        Keyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent key = new Intent(SearchType.this, SearchByKey.class);
                startActivity(key);
            }
        });

        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(SearchType.this, SearchByMap.class);
                startActivity(map);
            }
        });

        OccupancyRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rank = new Intent(SearchType.this, SearchByRank.class);
                startActivity(rank);
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SearchType.this, LoginPage.class);
                startActivity(back);
            }
        });


    }
}
