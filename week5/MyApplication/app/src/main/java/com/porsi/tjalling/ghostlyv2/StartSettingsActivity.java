package com.porsi.tjalling.ghostlyv2;

import android.app.Activity;
import android.os.Bundle;

public class StartSettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new startSettingsFragment())
                .commit();
    }
}
