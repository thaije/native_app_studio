package com.porsi.tjalling.ghostlyv2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class WinningActivity extends AppCompatActivity {

    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_screen);
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

        //"savedData" is the key that we will use in onCreate to get the saved data
        //mDataString is the string we want to save
        editor.putBoolean("turn", game.getTurn());
        editor.putString("word",game.getWord());

        // commit the edits
        editor.commit();
    }

}
