package com.porsi.tjalling.ghostlyv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.Locale;
/**
 * Author: Tjalling Haije
 * Student number: 10346236
 * University: University of Amsterdam
 * Course: Native App Studio
 */
public class WinningActivity extends AppCompatActivity {


    SharedPreferences settings;
    Players players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        setLocale();
        setContentView(R.layout.activity_winning_screen);

        // make a new players object with the existing highscores list
        if (!settings.contains("highscores"))
            players = new Players();
        else {
            try {
                players = new Players(settings.getString("highscores", ""));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        initialiseWinner();
        intialiseHighscores();


    }

    /*
     * Get the winner and print it on the screen
     */
    private void initialiseWinner() {
        Intent intent = getIntent();
        boolean winner = intent.getBooleanExtra("winner", false);
        int cause = intent.getIntExtra("cause", 1);
        String nicknameWinner;
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        TextView winningTextView = (TextView)findViewById(R.id.winnerText);
        TextView winningCause = (TextView)findViewById(R.id.winnerCause);


        // Get the nickname of the winner from shared preferences and set the winner text
        if (winner)
            nicknameWinner = settings.getString("nickname1","Player 1");
        else
            nicknameWinner = settings.getString("nickname2","Player 2");
        winningTextView.setText(nicknameWinner + " " + this.getString(R.string.winnaar));

        // Get the cause for winning and show it
        if (cause == 1)
            winningCause.setText(this.getString(R.string.winMethod1));
        else
            winningCause.setText(this.getString(R.string.winMethod2));

        // add player
        players.addPlayer(nicknameWinner);

    }


    /*
     * Get all highscores and print them on the screen
     */
    private void intialiseHighscores() {
        // make a new listview adapter
        ArrayAdapter<String> myAdapter =
            new ArrayAdapter<String>(this,
                                    android.R.layout.simple_list_item_1,
                                    players.getPlayers());

        // connect the adapter to the listview
        ListView myList= (ListView) findViewById(R.id.highscoreList);
        myList.setAdapter(myAdapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_winning_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*
     * Save the game state when paused or killed
     */
    @Override
    protected void onPause() {
        super.onPause();

        //save the data
        SharedPreferences.Editor editor = settings.edit();

        // save the highscores
        editor.putString("highscores",players.toJSON());

        // commit the edits
        editor.commit();
    }

    /*
     * Set language to value in preferences
     */
    private void setLocale() {

        // get preferences
        String lang = settings.getString("app_language", "");

        Locale locale = getBaseContext().getResources().getConfiguration().locale;
        Locale myLocale = new Locale(lang);

        // check if we need to change the language settings
        if (myLocale == locale)
            return;

        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }


    /*
     * Onclick listener which starts a new game
     */
    public void newGame(View view) {
        Intent i = new Intent(this,StartActivity.class);
        startActivity(i);
        finish();
    }



}
