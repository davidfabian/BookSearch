package com.example.d.booksearch;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;


/**
 * Created by d on 9/18/2017.
 * Basic loader class, built from autocomplete, fitted with corresponding names
 */

public class VolumeLoader extends AsyncTaskLoader {
    private static final String LOG_TAG = VolumeLoader.class.getName();

    //test
    String urlpassed;

    //inherited  constructor
    public VolumeLoader(Context context, String urlcreated) {
        super(context);


        //test
        urlpassed = urlcreated;
    }

    // start loading on starting loading...
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.e(LOG_TAG, "onStartLoading");
        forceLoad();
    }

    // added return type( list<volume>, and call queryutils to load the list, returns that list.
    @Override
    public List<Volume> loadInBackground() {

        List<Volume> volumes = QueryUtils.extractVolumes(urlpassed);
        return volumes;
    }
}
