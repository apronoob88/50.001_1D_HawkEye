package com.example.a50001_1d_hawkeye;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class SearchByRank extends AppCompatActivity {

    private Button Study;
    private Button Dining;
    private Button Sports;
    private Button BigEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_rank);

        Study=findViewById(R.id.buttonStudy);
        Dining=findViewById(R.id.buttonDining);
        Sports=findViewById(R.id.buttonSports);
        BigEvents=findViewById(R.id.buttonEvents);

        Study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent blk61 = new Intent(SearchByRank.this, Study.class);
                startActivity(blk61);
            }
        });
        Dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dining= new Intent (SearchByRank.this, Dinning.class);
                startActivity(dining);
            }
        });
    }
}
