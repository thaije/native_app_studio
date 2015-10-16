package com.porsi.tjalling.ghostlyv2;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

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

    private HashSet<String> oldFilter = new HashSet<String>();
    private HashSet<String> filter;
    private HashSet<String> lexicon = new HashSet<String>();
    private boolean exactWord = false;

    /*
     * Lexicon constructor.
     * open file and read into local data structure
     */
    Lexicon(Context context,String sourcePath) {

        // Get all assets
        AssetManager am = context.getAssets();

        // read the file into a hashmap
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
        HashSet<String> tempFilter;
        Iterator<String> it;

        // check if we need to check the whole lexicon, or we can filter our previous filter
        if (filter == null) {
            filter = new HashSet<String>();
            it = lexicon.iterator();
        }
        else {
            tempFilter = filter;
            filter = new HashSet<String>();
            it = tempFilter.iterator();
        }

        // loop through the possible words and add to the filter if it is possible
        while( it.hasNext() ) {
            String item = it.next();

            // add the word to the filter if it starts with the given input
            if( item.startsWith(word.toLowerCase()) )
                filter.add(item);

            // if there is a word that equals the typed word, save it
            if (item.equals(word.toLowerCase()))
                exactWord = true;
        }
    }


    /*
     * Return number of possible words remaining in filtered list
     */
    public int count(String word) {
        Log.e("WORD",word);
        if(word == "" || filter == null)
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

