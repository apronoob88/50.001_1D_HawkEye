package com.example.a50001_1d_hawkeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MoreDetailed extends AppCompatActivity {
    TextView name;
//    ImageView picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_detailed);
//        name = findViewById(R.id.tvMoreDetailed);
//        picture = findViewById(R.id.ivMoreDetailed);
//        DatabaseReference reff;
//
//
//        String setName= getIntent().getStringExtra("Name");
//        name.setText(setName);
//        int resourceId = getResources().getIdentifier(setName, "drawable", getPackageName());
//        picture.setImageResource(resourceId);
//        picture.setImageResource(R.drawable.canteen);
        //picture.setImageResource(getIntent().getIntExtra("resourceId",0));
//        final String checkLocation =getIntent().getStringExtra("Name");
//
//        reff = FirebaseDatabase.getInstance().getReference().child("Dining");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String numberInner = dataSnapshot.child(checkLocation).child("key").getValue().toString();
//                int resourceId = getResources().getIdentifier(numberInner, "drawable", getPackageName());
//                picture.setImageResource(resourceId);
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
