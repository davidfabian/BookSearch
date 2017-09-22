package com.example.d.booksearch;

/**
 * Created by d on 9/21/2017.
 * activity for settings fragment.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
//this is questionable

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    public static class VolumePreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            EditTextPreference edit_Pref = (EditTextPreference) findPreference(getString(R.string.settings_max_results_key));


//maxnoofresults getting and setting
            Preference maxNoOfResults = findPreference(getString(R.string.settings_max_results_key));
            bindPreferenceSummaryToValue(maxNoOfResults, getPreferenceValueToString(maxNoOfResults));
//sortingorder getting and setting
            Preference orderByKey = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderByKey, getPreferenceValueToString(orderByKey));
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringvalue = value.toString();
            Preference pref = preference;
            /*input validation, accepted input:
             (NOT maxresult preference OR (maxresult preference AND value between limits)
             if orderby preference(not maxresult, the input is from a list, no need for extra validation.
             if maxresult preference, check actual value against bounds before accepting it.)
            */

            if (pref != findPreference(getString(R.string.settings_max_results_key))
                    || (pref == findPreference(getString(R.string.settings_max_results_key)))
                    && Integer.parseInt(stringvalue) > 0
                    && Integer.parseInt(stringvalue) < 41) {
                //either orgerBy preference from the list, or maxresult preference AND valid number
                preference.setSummary(stringvalue);
                return true;
            }
            //out of bounds result
            Toast outOfBounds = Toast.makeText(getActivity(), "Number of Results must be between 1 and 40", Toast.LENGTH_SHORT);
            outOfBounds.show();
            return false;
        }

        private String getPreferenceValueToString(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            return preferences.getString(preference.getKey(), "");

        }

        private void bindPreferenceSummaryToValue(Preference preference, String string) {
            onPreferenceChange(preference, string);
        }
    }
}
