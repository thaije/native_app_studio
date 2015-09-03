package com.porsi.tjalling.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private int spotted;
    private int other;
    private int round;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetScore();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
