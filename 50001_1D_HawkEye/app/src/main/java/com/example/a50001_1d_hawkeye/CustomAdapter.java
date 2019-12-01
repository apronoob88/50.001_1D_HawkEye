package com.example.a50001_1d_hawkeye;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.android.gms.tasks.Tasks;


import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataItem> {
    Context context;
    int layoutResourceId;
    List<DataItem> data= null;
    DatabaseReference reff;
    int occupancyRateAttribute;
    ImageView occupancyRateImage;

    public void setImage(View convertView, int position){
        reff = FirebaseDatabase.getInstance().getReference().child("Locations");


        final String checkLocation =data.get(position).location;

        occupancyRateImage=convertView.findViewById(R.id.ivOccupancyRate);


        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String occupancyRateInner =dataSnapshot.child(checkLocation).child("occupancy rate").getValue().toString();
                float occ = Float.parseFloat(occupancyRateInner);
                int occupancyRate = Math.round(occ);
                if(occupancyRate<15){

                    occupancyRateImage.setImageResource(R.drawable.green);
                    Log.i("t","15");

                }
                else if(occupancyRate<50){
                    occupancyRateImage.setImageResource(R.drawable.orange);
                    Log.i("t","50");
                }
                //setOccupancyRateAttribute(60);
                else{
                    occupancyRateImage.setImageResource(R.drawable.red);
                    Log.i("t","else");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public CustomAdapter(Context context, int resource, List<DataItem> objects) {
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context= context;
        this.data=objects;
    }
    static class DataHolder
    {
        ImageView ivLocation;
        TextView tvLocation;
        ImageView ivOccupancyRate;
    }
    @NonNull
    //will be called for the number of times we are binding data wrote into listviews
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DataHolder holder = null;

        if(convertView == null) {
            //used to inflate view from external file
            // convert external file into a programmable object
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            //convert the resource into a programmable view
            convertView = inflater.inflate(layoutResourceId,parent,false);
            //creat object from xml file --> find view from the particular layout
            holder = new DataHolder();
            holder.ivLocation = (ImageView)convertView.findViewById(R.id.ivLocation);
            holder.tvLocation = (TextView)convertView.findViewById((R.id.tvLocation)) ;
            //holder.ivOccupancyRate=(ImageView)convertView.findViewById(R.id.ivOccupancyRate);

            //create the holder class to hole the reference of 2 different views
            //in 1 class
            //Reason: we want to pass the data in the convertView again
            convertView.setTag((holder));

        }
        else {
            holder=(DataHolder)convertView.getTag();
        }
//        reff = FirebaseDatabase.getInstance().getReference().child("Locations");
//        final String checkLocation =data.get(position).location;
//
//        occupancyRateImage=convertView.findViewById(R.id.ivOccupancyRate);
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String occupancyRateInner =dataSnapshot.child(checkLocation).child("occupancy rate").getValue().toString();
//                int occupancyRate = Integer.parseInt(occupancyRateInner);
//                if(occupancyRate<15){
//                    occupancyRateImage.setImageResource(R.drawable.green);
//                }
//                else if(occupancyRate<50){
//                    occupancyRateImage.setImageResource(R.drawable.orange);
//                }
//                //setOccupancyRateAttribute(60);
//                else{
//                    occupancyRateImage.setImageResource(R.drawable.red);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        if(occupancyRateAttribute<15){
//            holder.ivOccupancyRate.setImageResource(R.drawable.gom_gom);
//        }
//
//        else if(occupancyRateAttribute>=15&&occupancyRateAttribute<=80){
//            holder.ivOccupancyRate.setImageResource(R.drawable.canteen);
//        }
        DataItem dataItem = data.get(position);
        holder.tvLocation.setText(dataItem.location);
        holder.ivLocation.setImageResource(dataItem.id);
        setImage(convertView,position);

        return convertView;
    }
}


class item{
    private int i;

    item(int in){
        i = in;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}