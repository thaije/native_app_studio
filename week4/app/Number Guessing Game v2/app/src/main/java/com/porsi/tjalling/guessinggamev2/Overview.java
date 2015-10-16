package com.porsi.tjalling.guessinggamev2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Overview extends AppCompatActivity {
    Random rand = new Random();
    private int correctNumber;
    private int steps = 0;
    private int guessedNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        reset();
        SharedPreferences preferences = getSharedPreferences("pref", 0);
        steps = preferences.getInt("steps",1);
        setSteps(5-steps);
    }


    public void checkNumber(View view) {
        EditText edit =  (EditText) findViewById(R.id.guessedNumber);
        guessedNumber = Integer.parseInt(edit.getText().toString());

        System.out.println("Input:" + guessedNumber);

        //Start the new activity and send the scores
        Intent i = new Intent(this, CheckActivity.class);
        i.putExtra("guessedNumber",guessedNumber);
        i.putExtra("steps",steps);
        i.putExtra("correctNumber",correctNumber);
        startActivity(i);
        edit.setText("");

        // when back change the steps accordingly
        steps++;
        setSteps(5-steps);

        if(correctNumber == guessedNumber || steps == 5)
            reset();
        System.out.println("Steps:" + steps);

    }

    public void resetButton(View view) {
        reset();
    }

    // reset the
    public void reset() {
        steps = 0;
        guessedNumber = -1;
        correctNumber = rand.nextInt(25) + 1;
        System.out.println("correctNumber:" + correctNumber);
        setSteps(5);
    }

    private void setSteps(int stepsLeft) {
        TextView textViewStepsLeft = (TextView) findViewById(R.id.textView2);
        textViewStepsLeft.setText("Steps left: " + stepsLeft);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
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

    @Override
    protected void onPause() {
        super.onPause();

        //save the data
        SharedPreferences preferences = getSharedPreferences("pref", 0);
        SharedPreferences.Editor editor = preferences.edit();

        //"savedData" is the key that we will use in onCreate to get the saved data
        //mDataString is the string we want to save
        editor.putInt("steps", steps);

        // commit the edits
        editor.commit();
    }


    }
