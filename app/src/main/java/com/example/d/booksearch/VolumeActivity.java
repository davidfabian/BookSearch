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
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volume_list);
        String statusmessage;
        String passingurl = getIntent().getStringExtra("concUrlString");

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            statusmessage = getString(R.string.connecting);
        } else {
            statusmessage = getString(R.string.nointernet);
        }
        //find listview to inflate and attach listview to adapter

        ListView listView = (ListView) findViewById(R.id.list);
        mAdapter = new VolumeAdapter(this, new ArrayList<Volume>(), passingurl);
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
        String passingurl = getIntent().getStringExtra("concUrlString");
        return new VolumeLoader(this, passingurl);
    }

    @Override
    public void onLoadFinished(Loader<List<Volume>> loader, List<Volume> volumes) {
        Log.i(LOG_TAG, "load finished");

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.GONE);

        mAdapter.clear();


        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (volumes != null && !volumes.isEmpty()) {
            mAdapter.addAll(volumes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Volume>> loader) {
        Log.e(LOG_TAG, "loader reset");
        mAdapter.clear();
    }
}