package com.porsi.tjalling.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultActivity extends Activity {

    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        intent = getIntent();
        displayResult();

    }

    private void displayResult() {
        int spottedScore = 0;
        int otherScore = 0;

        // get the scores
        Bundle extras = intent.getExtras();
        if (extras != null) {
            spottedScore = extras.getInt("spottedScore");
            otherScore = extras.getInt("otherScore");
        }

        // change result depending on scores
        TextView text = (TextView) findViewById(R.id.resultText);
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.resultLayout);

        if(spottedScore > otherScore) {
            text.setText("Je houd van gevlekte koeien!");
            text.setBackgroundColor(Color.parseColor("#FF7200"));
            rl.setBackgroundResource(R.mipmap.spotted5);
        } else {
            text.setText("Je houd helemaal niet van koeien!");
            text.setBackgroundColor(Color.parseColor("#FF7200"));
            rl.setBackgroundResource(R.mipmap.other5);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
}
