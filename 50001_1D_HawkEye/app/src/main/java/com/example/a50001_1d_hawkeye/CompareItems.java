package com.example.a50001_1d_hawkeye;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.android.gms.tasks.Tasks;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class CompareItems implements Comparator<DataItem> {
    int o1;
    int o2;

    CompareItems() {

    }

    public int compare(DataItem a, DataItem b) {
        Log.i("DataA", a.getLocation());
//        Boolean check = true;
//        while(check == true){
//            if(o1!=101 && o2!=101){
//                check = false;
//
//            }
//            else{
//                setOccupancyRate1(a);
//                setOccupancyRate2(b);
//            }
//        }
//        int oo1 =o1;
//        int oo2 = o2;


        //Tasks.await(taskFromFirebase);
////        int A = getOccupancyRate(a);
////        int B = getOccupancyRate(b);
        setOccupancyRate1(a);
        Log.i("checkOtime", "O time is:");
        setOccupancyRate2(b);

        Log.i("yutongo1", String.valueOf(o1));
        Log.i("yutong02", String.valueOf(o2));
        if (o1 < o2) {
            Log.i("checkOtime2", "O time2 is");
            return -1;
        } else if (o1 == o2) {
            return 0;
        } else {
            return 1;
        }
    }

//        try
//        {
//            Thread.sleep(1000);
//        }
//        catch(InterruptedException ex)
//        {
//            Thread.currentThread().interrupt();
//        }

//        if (A<B){
//            return -1;
//        }
//
//        else if(A==B){
//            return 0;
//        }
//        else{
//            return 1;
//        }
//    public int getOccupancyRate(DataItem dataItem){
//
//        final String checkLocation= dataItem.getLocation();
//        final ArrayList<Integer> output= new ArrayList<>();
//        Log.i("dataItemLocation",checkLocation);
//        DatabaseReference reff= FirebaseDatabase.getInstance().getReference().child("Locations");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String occupancyRateInner =dataSnapshot.child(checkLocation).child("occupancy rate").getValue().toString();
//                Log.i("occupancyRateInner",occupancyRateInner);
//
//                o1=Integer.parseInt(occupancyRateInner);
//                output.add(Integer.parseInt(occupancyRateInner));
//                Log.i("checkO1",String.valueOf(o1));
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });
//        Log.i("outputOccupancy",String.valueOf(output.get(0)));
//        int oo1 = o1;
//        return oo1;
//        //return output.get(0);
//
//
//    }




    public void setOccupancyRate1(DataItem dataItem){

        final String checkLocation= dataItem.getLocation();
        Log.i("dataItemLocation",checkLocation);
        DatabaseReference reff= FirebaseDatabase.getInstance().getReference().child("Locations");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                String occupancyRateInner =dataSnapshot.child(checkLocation).child("occupancy rate").getValue().toString();
                Log.i("occupancyRateInner",occupancyRateInner);

                o1=Integer.parseInt(occupancyRateInner);
                Log.i("checkO1",String.valueOf(o1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }
    public void setOccupancyRate2(DataItem dataItem){

        final String checkLocation= dataItem.getLocation();
        DatabaseReference reff= FirebaseDatabase.getInstance().getReference().child("Locations");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String occupancyRateInner =dataSnapshot.child(checkLocation).child("occupancy rate").getValue().toString();

                o2=Integer.parseInt(occupancyRateInner);
                Log.i("checkO1",String.valueOf(o2));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }

}
