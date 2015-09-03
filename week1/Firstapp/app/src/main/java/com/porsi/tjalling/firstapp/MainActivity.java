package com.porsi.tjalling.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
    public int spotted;
    public int other;
    private int round;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetScore();
    }

    public int getSpottedScore() {
        return spotted;
    }

    public int getOtherScore() {
        return other;
    }

    // increment the spotted score if the left button is pushed and go to the next round
    public void clickButtonLeft(View view) {
        spotted++;
        nextRound();
    }

    // increment the other score if the right button is pushed and go to the next round
    public void clickButtonRight(View view) {
        other++;
        nextRound();
    }

    // go to the next round, or show the score
    private void nextRound() {
        // check if this was the last round
        if(round == 5) {
            System.out.println("Done");

            //Start the new activity and send the scores
            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("spottedScore",spotted);
            i.putExtra("otherScore",other);
            startActivity(i);
            finish();

        }

        // go to next round and render new images
        round++;
        renderImages();
    }

    // reset the score at the start of the app
    private void resetScore() {
        spotted = 0;
        other = 0;
        round = 1;
    }

    // Load the new images
    private void renderImages() {
        // get image views
        ImageView imgL = (ImageView) findViewById(R.id.imageLeft);
        ImageView imgR = (ImageView) findViewById(R.id.imageRight);

        String imageNameLeft = "spotted" + round;
        String imageNameRight = "other" + round;

        // find the image (simply spotted1/2/3 etc with the number the round
        int idLeft = getResources().getIdentifier(imageNameLeft, "mipmap", MainActivity.this.getPackageName());
        int idRight = getResources().getIdentifier(imageNameRight, "mipmap", MainActivity.this.getPackageName());

        imgL.setImageResource(idLeft);
        imgR.setImageResource(idRight);
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
