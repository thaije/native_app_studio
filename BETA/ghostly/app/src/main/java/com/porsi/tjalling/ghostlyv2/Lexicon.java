package com.porsi.tjalling.ghostlyv2;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Author: Tjalling Haije
 * Student number: 10346236
 * University: University of Amsterdam
 * Course: Native App Studio
 */
class Lexicon {

    private HashSet<String> filter = new HashSet<String>();
    private HashSet<String> lexicon = new HashSet<String>();
    private boolean exactWord = false;

    /*
     * Lexicon constructor.
     * open file and read into local data structure
     */
    Lexicon(Context context,String sourcePath) {

        // Get all assets
        AssetManager am = context.getAssets();

        try {
            InputStream is = am.open(sourcePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {
                lexicon.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("Done with lexicon:");
        System.out.println(sourcePath);

    }


    /*
     * Filter word list based on if the word starts with the given input
     */
    public void filter(String word) {
        reset();

        Iterator<String> it = lexicon.iterator();
        while( it.hasNext() ) {
            String item = it.next();

            // add the word to the filter if it starts with the given input
            if( item.startsWith(word.toLowerCase()) ) {
                filter.add(item);
                System.out.println(item);
            }

            // if there is a word that equals the typed word, save it
            if (item.equals(word.toLowerCase()))
                exactWord = true;
        }
    }


    /*
     * Return number of possible words remaining in filtered list
     */
    public int count(String word) {
        if(word == "")
            return lexicon.size();

        return filter.size();
    }

    public boolean exactMatch() {
        return exactWord;
    }


    /*
     * Returns last remaining word in filtered list
     */
    public String result() {
        return "Last word";
    }


    /*
     * Remove filter and reset to original lexicon
     */
    public void reset() {
        filter.clear();
        exactWord = false;
    }

}

