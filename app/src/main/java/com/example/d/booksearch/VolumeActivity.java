package com.example.d.booksearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.d.booksearch.QueryUtils.extractVolumes;

/**
 * Created by d on 9/17/2017.
 * listview, populated with books according to search term
 */

public class VolumeActivity extends AppCompatActivity {

    private VolumeAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);


    }
}
