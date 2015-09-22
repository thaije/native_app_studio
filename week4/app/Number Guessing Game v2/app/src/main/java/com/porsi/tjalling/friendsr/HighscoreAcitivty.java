package com.porsi.tjalling.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class HighscoreAcitivty extends AppCompatActivity {


    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_acitivty);

        intent = getIntent();
        displayText();
    }

    private void displayText() {
        TextView winningText = (TextView) findViewById(R.id.winningText);
        int won = -1;
        int steps = -1;

        // get the data
        Bundle extras = intent.getExtras();
        if (extras != null) {
            won = extras.getInt("won");
        }



        System.out.println("Won:" + won);

        if(won == 1) {
            winningText.setText("You Won! View the (dummy) highscores below");
        }
        else {
            winningText.setText("You lost! View the (dummy) highscores below");
        }

    }


    // ga back for new game
    public void newGame(View view) {
        super.finish();
    }

}
