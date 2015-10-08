package com.porsi.tjalling.ghostlyv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameActivity extends AppCompatActivity {

    Game game;
    SharedPreferences settings;
    String lexiconLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get the initial settings
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        lexiconLanguage = settings.getString("lexicon_language", "");

        // Create the lexicon and Game objects
        Lexicon lexicon = new Lexicon(this, lexiconLanguage);
        this.game = new Game(lexicon, this);

        setTextViews();
    }


    /*
     * Reset the game
     */
    public void resetGame() {

    }



    /*
     * Onclick of guess button, check the input
     */
    public void checkGuess(View view) {
        // Check input
        String letter = checkLetter();
        System.out.print("Input was:");
        System.out.println(letter);

        // return a toast to the user when the input is wrong
        if(letter == "") {
            CharSequence text = "Input 1 letter please";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            return;
        }

        // If the input is correct, add it to the word and check the word
        checkWord(letter);

        game.nextTurn();
        setTextViews();
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
    private void checkWord(String letter) {
        String guessWord = game.getWord() + letter;
        System.out.print("New word:");
        System.out.println(guessWord);

        game.guess(guessWord);

        // check if the game has ended, otherwise go to the next turn
        if (game.ended()) {
            Intent i = new Intent(this, WinningActivity.class);
            startActivity(i);
        }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
