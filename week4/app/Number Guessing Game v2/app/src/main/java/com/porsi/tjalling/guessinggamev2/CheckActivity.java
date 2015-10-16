package com.porsi.tjalling.guessinggamev2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CheckActivity extends AppCompatActivity {
    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        intent = getIntent();
        displayResult();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check, menu);
        return true;
    }

    private void displayResult(){
        int steps = -1;
        int guessedNumber = -1;
        int correctNumber = -1;

        // get the data
        Bundle extras = intent.getExtras();
        if (extras != null) {
            steps = extras.getInt("steps");
            guessedNumber = extras.getInt("guessedNumber");
            correctNumber = extras.getInt("correctNumber");
        }

        TextView edit =  (TextView) findViewById(R.id.result);
        System.out.println("Guessed:" + guessedNumber + ". Correct:" + correctNumber);

        // win, display text and exit
        if(guessedNumber == correctNumber) {
            viewHighscores(1,steps);
        }

        // lose, display text and exit
        else if(steps == 4) {
            viewHighscores(0, steps);
        }


        // Check if higher or lower
        else if(guessedNumber < correctNumber) {
            edit.setText("Higher!");
            //finish();
            //super.finish();
        }
        else if (guessedNumber > correctNumber){
            edit.setText("Lower!");
            //finish();
            //super.finish();
        }

    }

    public void tryAgain(View view) {
        finish();
    }

    // Go to highscore screen
    public void viewHighscores(int won, int steps) {
        Intent i = new Intent(this, HighscoreAcitivty.class);
        i.putExtra("won",won);
        startActivity(i);
        finish();
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
