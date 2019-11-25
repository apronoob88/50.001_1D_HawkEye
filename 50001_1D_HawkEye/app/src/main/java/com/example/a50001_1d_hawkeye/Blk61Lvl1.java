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

    private Button Gym;
    private Button ISH1;
    private Button ISH2;
    private DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blk61_lvl1);

        Gym = findViewById(R.id.gymButton);
        ISH1 = findViewById(R.id.ish1Button);
        ISH2 = findViewById(R.id.ish2Button);


        //TODO Yutong pls link the firebase info for ISH1 and ISH2
        Gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Gym");
                intent.putExtra("picture",R.drawable.gym);

                intent.setClass(Blk61Lvl1.this,Location.class);
                startActivity(intent);
            }
        });


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
