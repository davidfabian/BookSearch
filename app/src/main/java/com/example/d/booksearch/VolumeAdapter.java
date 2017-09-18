package com.example.d.booksearch;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by d on 9/17/2017.
 * adapter class to connect the volumelist with the volumeactivity display-class
 */

public class VolumeAdapter extends ArrayAdapter<Volume> {
    public VolumeAdapter(Activity context, ArrayList<Volume> volumes, String passingurl) {
        super(context, 0, volumes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        //get current item in the list loaded
        final Volume currentVolume = getItem(position);

//add title and author content to corresponding textviews
        TextView titleTextView = listItemView.findViewById(R.id.title_textview);
        titleTextView.setText(currentVolume.getTitle());

        TextView authorTextView = listItemView.findViewById(R.id.author_textview);
        authorTextView.setText(currentVolume.getAuthor());

        return listItemView;
    }

}
