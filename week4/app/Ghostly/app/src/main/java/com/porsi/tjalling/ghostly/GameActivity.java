package com.porsi.tjalling.ghostly;

/**
 * Author: Tjalling Haije
 * Student number: 10346236
 * University: University of Amsterdam
 * Course: Native App Studio
 * File description:
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GameActivity extends AppCompatActivity {

    Game game;
    Lexicon lexicon;
//    SharedPreferences preferences = getSharedPreferences("pref", 0);
//    SharedPreferences.Editor editor = preferences.edit();

//    SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//    String strUserName = SP.getString("username", "NA");
//    boolean bAppUpdates = SP.getBoolean("applicationUpdates", false);
//    String downloadType = SP.getString("downloadType", "1");
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        launchStartscreen();

        // Create the lexicon and Game objects
        this.lexicon = new Lexicon(this, "dutch.txt");
        this.game = new Game(lexicon);
    }


    // Launch the startscreen to get the intial settings
    private void launchStartscreen() {
        //Start the new activity and send the scores
        Intent i = new Intent(this, StartActivity.class);
        startActivity(i);
    }
}
