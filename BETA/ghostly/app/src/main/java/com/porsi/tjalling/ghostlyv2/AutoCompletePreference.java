package com.porsi.tjalling.ghostlyv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.json.JSONException;
import java.util.Arrays;

/**
 * Author: Tjalling Haije based
 * Student number: 10346236
 * University: University of Amsterdam
 * Course: Native App Studio
 * Content: A class which extends the edittextpreference to make it compatible with
 * auto-completion.
 */
public class AutoCompletePreference extends EditTextPreference {

    private static AutoCompleteTextView mEditText = null;
    public static String TAG = AutoCompletePreference.class.getSimpleName();

    public AutoCompletePreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        // make a new autocomplete field and get the shared preferences
        mEditText = new AutoCompleteTextView(context, attrs);
        mEditText.setThreshold(0);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        Players players = null;

        // Get all previous players, or if not existing make a new variable
        if (!settings.contains("highscores"))
            players = new Players();
        else {
            try {
                players = new Players(settings.getString("highscores", ""));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //The adapter of your choice
        String[] errorSoon = new String[2];
        errorSoon[0] = "Hello";
        errorSoon[1] = "World";

        System.out.print("Fill adapter with:");
        System.out.println(Arrays.toString(players.getPlayers()));
        System.out.println(Arrays.toString(errorSoon));

        // set the adapter to fill the dropdown box with suggestions
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                                                        android.R.layout.simple_dropdown_item_1line,
                                                        players.getPlayers());
        mEditText.setAdapter(adapter);
    }


    /*
     * Bind the dialogview to a autocomplete textview.
     */
    @Override
    protected void onBindDialogView(View view) {
        AutoCompleteTextView editText = mEditText;
        editText.setText(getText());

        ViewParent oldParent = editText.getParent();
        if (oldParent != view) {
            if (oldParent != null) {
                ((ViewGroup) oldParent).removeView(editText);
            }
            onAddEditTextToDialogView(view, editText);
        }
    }

    /*
     * Get the value which the user has choosen as he exists the dialog
     */
    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            String value = mEditText.getText().toString();
            if (callChangeListener(value)) {
                setText(value);
            }
        }
    }
}