package com.example.d.booksearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by d on 9/17/2017.
 * listview, populated with books according to search term
 */

public class VolumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volume_list);

        //get the volumes in the list
        ArrayList<Volume> volumes = (ArrayList<Volume>) QueryUtils.extractVolumes();

        //create adapter
        VolumeAdapter volumeAdapter = new VolumeAdapter(this, volumes);

        //find listview to inflate and attach listview to adapter
        ListView listView = (ListView) findViewById(R.id.list);
        Log.e("arraylist volume loaded", "" + listView.getContext());
        listView.setAdapter(volumeAdapter);
    }
}
