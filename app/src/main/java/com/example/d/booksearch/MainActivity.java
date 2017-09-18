package com.example.d.booksearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String GOOGLE_BOOKS_API_STUMP = "https://www.googleapis.com/books/v1/volumes?q=";
    // magyar&maxResults=10"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SearchView searchView = (SearchView) findViewById(R.id.searchview_volume);
        final Button searchButton = (Button) findViewById(R.id.button_search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.e("onQueryTextChange", "called");
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {

                //dummy button action, to be replaced by actual search
                Intent volumeIntent = new Intent(MainActivity.this, VolumeActivity.class);
                startActivity(volumeIntent);
                return false;
            }

        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //intent to start search, display results
                Intent volumeIntent = new Intent(MainActivity.this, VolumeActivity.class);
                startActivity(volumeIntent);
            }
        });
    }

    public String SearchTerm() {
        SearchView searchView = (SearchView) findViewById(R.id.searchview_volume);
        return searchView.getQuery().toString();
    }


}
