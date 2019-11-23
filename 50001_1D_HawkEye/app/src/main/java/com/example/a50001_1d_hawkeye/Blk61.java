package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Blk61 extends AppCompatActivity {

    private ImageButton BackButton;
    private Button Level1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blk61);

        BackButton = findViewById(R.id.backButton);
        Level1 = findViewById(R.id.blk61l1);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Blk61.this, SearchByMap.class);
                startActivity(back);
            }
        });

        Level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent blk61lvl1 = new Intent(Blk61.this, Blk61Lvl1.class);
                startActivity(blk61lvl1);
            }
        });
    }
}
