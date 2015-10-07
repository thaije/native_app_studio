package com.porsi.tjalling.ghostlyv2;

/**
 * Author: Tjalling Haije
 * Student number: 10346236
 * University: University of Amsterdam
 * Course: Native App Studio
 * File description:
 */


public class Game {
    private boolean turn = false;
    private boolean ended = false;
    private boolean winner = false;
    private String guessedWord = "";
    Lexicon lexicon;

    Game(Lexicon lexicon) {
        this.lexicon = lexicon;
    }

    // Checks guess in lexicon
    public void guess (String guessedWord) {
        this.guessedWord = guessedWord;

        lexicon.filter(guessedWord);

    }


    // Return true = player1, or false = player2
    public boolean turn() {

        return turn;
    }


    // Returns true if the game has ended
    public boolean ended() {

        if(lexicon.count(guessedWord) == 1) {
            return true;
        }

        return false;
    }


    // Returns winner: true = player1, false = player2
    public boolean winner() {

        return true;
    }

}
