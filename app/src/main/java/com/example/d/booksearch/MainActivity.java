package com.example.d.booksearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

                TextView dummyone = (TextView) findViewById(R.id.DummyTextView);
                dummyone.setText(searchView.getQuery());
                dummyone.setVisibility(View.VISIBLE);
                // Do your task here

                return false;
            }

        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //testing button onclick
                TextView dummyone = (TextView) findViewById(R.id.DummyTextView);
                dummyone.setText(searchView.getQuery());
                dummyone.setVisibility(View.VISIBLE);
            }
        });
    }


}
