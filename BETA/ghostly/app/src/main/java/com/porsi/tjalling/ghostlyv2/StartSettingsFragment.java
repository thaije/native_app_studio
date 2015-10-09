package com.porsi.tjalling.ghostlyv2;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Author: Tjalling Haije
 * Student number: 10346236
 * University: University of Amsterdam
 * Course: Native App Studio
 */

public class StartSettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.start_prefrences);
    }
}
