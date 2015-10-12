package com.porsi.tjalling.ghostlyv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
/**
 * Author: Tjalling Haije
 * Student number: 10346236
 * University: University of Amsterdam
 * Course: Native App Studio
 */
public class GameActivity extends AppCompatActivity {

    Game game;
    SharedPreferences settings;
    String lexiconLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale();
        setContentView(R.layout.activity_game);

        // Get the initial settings
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        lexiconLanguage = this.getString(R.string.lexicon_path);

        // Create the lexicon and Game objects
        Lexicon lexicon = new Lexicon(this, lexiconLanguage);
        this.game = new Game(lexicon, this);

        setTextViews();
        setOnEnterListener();
    }


    /*
     * Reset the game
     */
    public void resetGame() {
        game.lexicon = null;
        game = null;
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("word");
        editor.remove("turn");
        editor.commit();
    }

    /*
     * Onclick listener of guess button, check the input
     */
    public void guessButton(View view) {
        checkGuess();
    }



    /*
     * Check the input and add it to the word if in correct format
     */
    public void checkGuess() {
        // Check input
        String letter = checkLetter();
        System.out.print("Input was:");
        System.out.println(letter);

        // return a toast to the user when the input is wrong
        if (letter == "") {
            CharSequence text = "Input 1 letter please";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            return;
        }

        // If the input is correct add it to the word and check the word and continue
        // if it hasn't ended yet
        if (checkWord(letter)) {
            game.nextTurn();
            setTextViews();
        }
    }


    /*
     * Onkey listener for enter button
     */
    public void setOnEnterListener() {
        EditText editText = (EditText) findViewById(R.id.nextLetter);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    checkGuess();
                    handled = true;
                }
                return handled;
            }
        });
    }


    /*
     * Set the next turn by setting it in the Game class and setting all text views
     */
    public void setTextViews() {
        TextView turnText = (TextView)findViewById(R.id.turnTextView);
        if(game.getTurn())
            turnText.setText(settings.getString("nickname1",""));
        else
            turnText.setText(settings.getString("nickname2",""));

        TextView currentWord = (TextView)findViewById(R.id.ghostlyWord);
        currentWord.setText(game.getWord());

        TextView possWords = (TextView)findViewById(R.id.possibleWords);
        possWords.setText(game.possibleWordsLeft(game.getWord()) + "");

        EditText letterInput = (EditText)findViewById(R.id.nextLetter);
        letterInput.setText("");
    }


    /*
     * Check if the inputted letter is valid
     */
    private String checkLetter() {
        // Get the input
        EditText guessedLetter = (EditText)findViewById(R.id.nextLetter);
        String input = guessedLetter.getText().toString().toUpperCase().trim();

        // check if the letter is an A-Z character
        if(input.length() == 1) {
            char letter = input.charAt(0);
            if(65 <= letter && letter <= 90)
                return input;
        }

        return "";
    }


    /*
     * Check the new word in the dictionary, and check for winners
     */
    private boolean checkWord(String letter) {
        String guessWord = game.getWord() + letter;
        System.out.print("New word:");
        System.out.println(guessWord);

        game.guess(guessWord);
        boolean winner = game.winner();

        // check if the game has ended, otherwise go to the next turn
        int ended = game.ended();
        if (ended > 0) {
            Intent i = new Intent(this, WinningActivity.class);
            i.putExtra("winner", winner);
            i.putExtra("cause",ended);
            resetGame();
            startActivity(i);
            finish();
            return false;
        }

        return true;
    }

    /*
     * Save the game state when paused or killed
     */
    @Override
    protected void onPause() {
        super.onPause();

        //save the data
        SharedPreferences.Editor editor = settings.edit();

        // save the gamedata
        if (game != null) {
            editor.putBoolean("turn", game.getTurn());
            editor.putString("word", game.getWord());

            // commit the edits
            editor.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                finish();
                return true;
            case R.id.newgame_settings:
                resetGame();
                Intent j = new Intent(this, StartActivity.class);
                startActivity(j);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    /*
     * Set language to value in preferences
     */
    private void setLocale() {

        // get preferences
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
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


}
