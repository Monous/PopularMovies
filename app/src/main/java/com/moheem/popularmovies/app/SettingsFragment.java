package com.moheem.popularmovies.app;

import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.Preference;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Have to make this static call for the initial launch of the app because
        // sharedPreferences.getString(key, null) will return null despite the default value
        // being set in the XML. This allows us not to have to wait until the preference activity is invoked
        // http://stackoverflow.com/questions/7342557/listpreference-default-value-not-showing-up
        PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);
        addPreferencesFromResource(R.xml.preferences);

        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_sort_order_key)));
    }

    private void bindPreferenceSummaryToValue(Preference preference){
        preference.setOnPreferenceChangeListener(this);

        onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(), ""));
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(newValue.toString());
            if (prefIndex >= 0) preference.setSummary(listPreference.getEntries()[prefIndex]);
        } else {
            preference.setSummary(newValue.toString());
        }
        return true;
    }
}
