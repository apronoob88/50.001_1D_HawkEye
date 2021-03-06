package com.example.a50001_1d_hawkeye;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;



//Creating a CustomAdapter that can hold the references of 2 different views
//namely an ImageView and a TextView in 1 class
public class CustomAdapter extends ArrayAdapter<DataItem> {
    Context context;
    int layoutResourceId;
    List<DataItem> data;

    public CustomAdapter(Context context, int resource, List<DataItem> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context= context;
        this.data=objects;
    }
    static class DataHolder {
        ImageView ivLocation;
        TextView tvLocation;
    }
    //will be called for the number of times we are binding data wrote into listviews in searchByKey
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DataHolder holder = null;

        if(convertView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId,parent,false);

            holder = new DataHolder();
            holder.ivLocation = (ImageView)convertView.findViewById(R.id.ivLocation);
            holder.tvLocation = (TextView)convertView.findViewById((R.id.tvLocation)) ;

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
