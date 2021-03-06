package com.porsi.tjalling.ghostlyv2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Author: Tjalling Haije
 * Student number: 10346236
 * University: University of Amsterdam
 * Course: Native App Studio
 */
public class Players {

    HashMap<String, Integer> highscores;

    /*
     * Constructor for Game class
     */
    Players() {
        highscores = new HashMap<String, Integer>();
    }

    /*
     * Constructor for Game class from existing data
     */
    Players(String highscores) throws JSONException {
        // convert the json string to a json object
        JSONObject json = new JSONObject(highscores);

        this.highscores = toHashMap(json);
    }


    /*
     * Add a new player to the highscores if it doesn't exist yet
     */
    public void addPlayer(String nickname){
        if (!highscores.containsKey(nickname))
           highscores.put(nickname,0);
    }


    /*
     * Increment the players score by 1
     */
    public void addVictory(String nickname){
        int newHighscore = highscores.get(nickname) + 1;
        highscores.put(nickname,newHighscore);

    }


    /*
     * Return all players as a list
     */
    public String[] getPlayers() {

        String[] strings = highscores.keySet().toArray(new String[highscores.size()]);

        return strings;
    }

    /*
     * Get all highscores by returning the TreeMap
     */
    public HashMap<String,Integer> getHighscores() {
        return highscores;
    }



    /*
     * Return the highscores as a json string
     */
    public String toJSON() {
        return  (new JSONObject(highscores)).toString();
    }


    /*
     * Converts a JSON object to a hashmap used to intialize the highscore hashmap
     */
    private static HashMap<String, Integer> toHashMap(JSONObject object) throws JSONException {
        HashMap<String, Integer> map = new HashMap<>();

        // loop through all of the json objects
        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            // add each object to the hash map
            map.put(key, (int)value);
        }
        return map;
    }

}
