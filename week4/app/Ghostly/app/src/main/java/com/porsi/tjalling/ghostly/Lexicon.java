package com.porsi.tjalling.ghostly;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.provider.MediaStore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Tjalling Haije
 * Student number: 10346236
 * University: University of Amsterdam
 * Course: Native App Studio
 * File description:
 */
class Lexicon {

    private HashSet<String> filter = new HashSet<String>();
    private HashSet<String> lexicon = new HashSet<String>();

    // open file and read into local data structure
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

        System.out.println("Done");

    }


    // Filter word list based on word
    public void filter(String word) {

    }


    // Return number of possible words remaining in filtered list
    public int count(String word) {

        return 1;
    }


    // Returns last remaining word in filtered list
    public String result() {
        return "Last word";
    }


    // Remove filter and reset to original lexicon
    public void reset() {

    }

}

