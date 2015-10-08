package com.porsi.tjalling.ghostlyv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Random;

/**
 * Author: Tjalling Haije
 * Student number: 10346236
 * University: University of Amsterdam
 * Course: Native App Studio
 * File description:
 */


public class Game {
    private boolean turn = false;
    private String word = "";
    private boolean ended = false;
    private boolean winner = false;
    Lexicon lexicon;


    /*
     * Constructor for Game class
     */
    Game(Lexicon lexicon, Context context) {
        this.lexicon = lexicon;
        initialiseVariables(context);
    }


    /*
     * Check if turn and word variable exist from a previous game, intialise if not
     */
    private void initialiseVariables(Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        // check if turn and the word have been saved from a previous game
        if(settings.contains("word")) {
            word = settings.getString("word", "");
            turn = settings.getBoolean("turn", true);
        }
        else {
            Random rand = new Random();
            turn = rand.nextBoolean();
        }
    }

    /*
     * Checks guess in lexicon
     */
    public void guess (String guessedWord) {
        this.word = guessedWord;

        lexicon.filter(guessedWord);

    }

    /*
     * Setter for the word variable
     */
    public void setWord (String word) {
        this.word = word;
    }


    /*
     * Getter for the word variable
     */
    public String getWord() {
        return word;
    }


    /*
     * Getter for turn variable. True is player 1
     */
    public boolean getTurn() {
        return turn;
    }


    /*
     * Go to the next turn
     */
    public void nextTurn() {
        turn = !turn;
    }


    /*
     * Return true if the game has ended (1 possibility left)
     */
    public boolean ended() {

        if(possibleWordsLeft(word) <= 1) {
            return true;
        }

        return false;
    }


    /*
     * Returns winner: true = player1, false = player2
     */
    public boolean winner() {
        winner = !turn;
        return winner;
    }


    /*
     * Returns amount of possible words left
     */
    public int possibleWordsLeft(String guessedWord) {
        return lexicon.count(guessedWord);
    }

}
