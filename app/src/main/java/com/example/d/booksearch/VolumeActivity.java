package com.example.d.booksearch;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by d on 9/17/2017.
 * listview, populated with books according to search term
 */

public class VolumeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Volume>> {
    private static final String LOG_TAG = VolumeActivity.class.getName();
    private static final int VOLUME_LOADER_ID = 1;
    private VolumeAdapter mAdapter;
    private String statusmessage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volume_list);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            statusmessage = getString(R.string.no_author);
        } else {
            statusmessage = getString(R.string.nointernet);
        }

        Log.e(LOG_TAG, "volumeactivity created");

        //get the volumes in the list
        //ArrayList<Volume> volumes = (ArrayList<Volume>) QueryUtils.extractVolumes();

        //create adapter
        //VolumeAdapter volumeAdapter = new VolumeAdapter(this, volumes);

        //find listview to inflate and attach listview to adapter
        ListView listView = (ListView) findViewById(R.id.list);
        mAdapter = new VolumeAdapter(this, new ArrayList<Volume>());
        listView.setAdapter(mAdapter);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(VOLUME_LOADER_ID, null, this);

    }

    @Override
    public Loader<List<Volume>> onCreateLoader(int i, Bundle bundle) {
        return new VolumeLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Volume>> loader, List<Volume> volumes) {
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (volumes != null && !volumes.isEmpty()) {
            Log.e(LOG_TAG, "loader notempty & notnull");
            mAdapter.addAll(volumes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Volume>> loader) {
        Log.e(LOG_TAG, "loader reset");
        mAdapter.clear();
    }
}