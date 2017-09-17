package com.example.d.booksearch;

import android.app.LoaderManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // implements LoaderManager.LoaderCallbacks<List<Volume>> {

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

                //dummy button action, to be replaced by actual search
                TextView dummyone = (TextView) findViewById(R.id.DummyTextView);
                dummyone.setText(searchView.getQuery());
                dummyone.setVisibility(View.VISIBLE);
                Volume egykonyv = new Volume("az iro", "a konyv cime");
                dummyone.setText(egykonyv.getTitle());
                QueryUtils.extractVolumes();
            }
        });
    }

    public String SearchTerm() {
        SearchView searchView = (SearchView) findViewById(R.id.searchview_volume);
        return searchView.getQuery().toString();
    }
}
