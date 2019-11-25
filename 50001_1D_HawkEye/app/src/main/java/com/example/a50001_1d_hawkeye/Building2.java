package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Building2 extends AppCompatActivity {

    private Button Level1;
    private ImageButton Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building2);


        Level1 = findViewById(R.id.b2l1);

        Level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B1L1 = new Intent(Building2.this, Building2Lvl1.class);
                startActivity(B1L1);
            }
        });

    }
}
