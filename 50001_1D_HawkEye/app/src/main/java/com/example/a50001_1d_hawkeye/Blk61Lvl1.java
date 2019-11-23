package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Blk61Lvl1 extends AppCompatActivity {

    private ImageButton BackButton;
    private Button ISH1;
    private Button ISH2;
    private DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blk61_lvl1);

        BackButton=findViewById(R.id.backButton);
        ISH1 = findViewById(R.id.ish1Button);
        ISH2 = findViewById(R.id.ish2Button);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Blk61Lvl1.this, Blk61.class);
                startActivity(back);
            }
        });
        //TODO Yutong pls link the firebase info for ISH1 and ISH2
        ISH1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Indoor Sports Hall 1");
                intent.putExtra("picture",R.drawable.ish1);

                intent.setClass(Blk61Lvl1.this,Location.class);
                startActivity(intent);
            }
        });

        ISH2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Indoor Sports Hall 2");
                intent.putExtra("picture",R.drawable.ish2);

                intent.setClass(Blk61Lvl1.this,Location.class);
                startActivity(intent);
            }
        });
    }
}
