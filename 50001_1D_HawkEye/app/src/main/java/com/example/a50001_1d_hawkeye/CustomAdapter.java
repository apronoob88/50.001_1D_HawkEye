package com.example.a50001_1d_hawkeye;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataItem> {
    Context context;
    int layoutResourceId;
    List<DataItem> data= null;

    public CustomAdapter(Context context, int resource,List<DataItem> objects) {
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context= context;
        this.data=objects;
    }
    static class DataHolder
    {
        ImageView ivLocation;
        TextView tvLocation;
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

            //create the holder class to hole the reference of 2 different views
            //in 1 class
            //Reason: we want to pass the data in the convertView again
            convertView.setTag((holder));

        }
        else {
            holder=(DataHolder)convertView.getTag();
        }

        DataItem dataItem = data.get(position);
        holder.tvLocation.setText(dataItem.location);
        holder.ivLocation.setImageResource(dataItem.id);

        return convertView;
    }
}