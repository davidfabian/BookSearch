package com.example.d.booksearch;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by d on 9/18/2017.
 * Basic loader class, built from autocomplete, fitted with corresponding names
 */

public class VolumeLoader extends AsyncTaskLoader {

    //inferited  constructor
    public VolumeLoader(Context context) {
        super(context);
    }

    // start loading on starting loading...
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    // added return type( list<volume>, and call queryutils to load the list, returns that list.
    @Override
    public List<Volume> loadInBackground() {
        List<Volume> volumes = QueryUtils.extractVolumes();
        return volumes;
    }
}
