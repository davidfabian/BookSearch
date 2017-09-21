package com.example.d.booksearch;

/**
 * Created by d on 9/21/2017.
 * activity for settings fragment.
 */

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

//this is questionable

public class SettingsActivity extends AppCompatActivity {
    @Override
    //nullable questionable
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    public static class VolumePreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);
        }
    }
}
