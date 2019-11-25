package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Blk57 extends AppCompatActivity {

    // Test commit



    private Button Level2;
    private ImageButton Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blk57);


        Level2 = findViewById(R.id.blk57l2);


        Level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B1L1 = new Intent(Blk57.this, Blk57Lvl2.class);
                startActivity(B1L1);
            }
        });

    }
}