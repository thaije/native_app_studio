package com.porsi.tjalling.ghostlyv2;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class startSettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.start_prefrences);
    }
}
