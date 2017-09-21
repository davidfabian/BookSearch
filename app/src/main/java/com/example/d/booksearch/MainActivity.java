package com.example.d.booksearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String GBAPI_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    private static final String GBAPI_FINAL_URL = "&maxResults=40";
    // magyar&maxResults=10"

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SearchView searchView = (SearchView) findViewById(R.id.searchview_volume);
        final Button searchButton = (Button) findViewById(R.id.button_search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                CallIntent();
                return false;
            }

        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check for valid search term, display warning if no text entered
                if (SearchTerm().trim().length() < 1) {
                    Toast toast = Toast.makeText(getApplicationContext(), getText(R.string.no_search_term_error), Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                CallIntent();
                }
            }
        });
    }

    public String SearchTerm() {
        String searchterm = null;
        SearchView searchView = (SearchView) findViewById(R.id.searchview_volume);
        searchterm = searchView.getQuery().toString();
        return searchterm;
    }

    public String UrlCreator() {
        SharedPreferences sharedprefs = PreferenceManager.getDefaultSharedPreferences(this);
        String maxresults = sharedprefs.getString(
                getString(R.string.settings_max_results_key),
                getString(R.string.settings_max_results_default));
        String orderBy = sharedprefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        Uri baseUri = Uri.parse(GBAPI_BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", SearchTerm().trim());
        uriBuilder.appendQueryParameter("maxResults", maxresults);
        uriBuilder.appendQueryParameter("orderBy", orderBy);
        return uriBuilder.toString();
    }

    public void CallIntent() {
        String fullUrl = UrlCreator();
        Intent volumeIntent = new Intent(MainActivity.this, VolumeActivity.class);
        volumeIntent.putExtra("concUrlString", fullUrl);
        startActivity(volumeIntent);
    }
}
