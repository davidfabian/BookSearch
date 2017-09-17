package com.example.d.booksearch;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by d on 9/17/2017.
 * adapter class to connect the volumelist with the volumeactivity display-class
 */

public class VolumeAdapter extends ArrayAdapter<Volume> {
    public VolumeAdapter(Activity context, List<Volume> volumes) {
        super(context, 0, volumes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final Volume currentVolume = getItem(position);

        QueryUtils.extractVolumes();

        TextView titleTextView = listItemView.findViewById(R.id.title_textview);
        titleTextView.setText(currentVolume.getTitle());

        TextView authorTextView = listItemView.findViewById(R.id.author_textview);
        authorTextView.setText(currentVolume.getAuthor());

        return listItemView;
    }

}
