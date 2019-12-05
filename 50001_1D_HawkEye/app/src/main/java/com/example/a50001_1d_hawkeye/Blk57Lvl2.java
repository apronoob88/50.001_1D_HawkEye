package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Blk57Lvl2 extends AppCompatActivity {


    private Button MPHButton;
    private Button StudyRoom;
    private DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blk57_lvl2);


        MPHButton = findViewById(R.id.mphButton);
        StudyRoom = findViewById(R.id.studyRoomButton);

        //TODO Yutong pls link the firebase info for MPH
        MPHButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Multi-Purpose Hall");
                intent.putExtra("picture",R.drawable.mph);

                intent.setClass(Blk57Lvl2.this,Location.class);
                startActivity(intent);
            }
        });

        StudyRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Locations");
                final Intent intent = new Intent();

                intent.putExtra("Location", "Study Room");
                intent.putExtra("picture",R.drawable.study_room);

                intent.setClass(Blk57Lvl2.this,Location.class);
                startActivity(intent);
            }
        });
    }
}
