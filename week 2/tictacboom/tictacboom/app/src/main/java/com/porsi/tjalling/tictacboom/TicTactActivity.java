package com.porsi.tjalling.tictacboom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TicTactActivity extends AppCompatActivity {
    // 1 = user, 2 = computer
    private int turn = 0;
    private int[][] board = new int[3][3];
    Map<Integer, String> map = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tact);
        resetBoard();
    }

    // reset the board
    private void resetBoard() {
        turn = 0;
        board = new int[3][3];
        map = new HashMap<Integer, String>();
        map.put(R.id.topLeft,"00");
        map.put(R.id.topCenter,"01");
        map.put(R.id.topRight,"02");
        map.put(R.id.centerLeft,"10");
        map.put(R.id.centerCenter,"11");
        map.put(R.id.centerRight,"12");
        map.put(R.id.bottomLeft,"20");
        map.put(R.id.bottomCenter,"21");
        map.put(R.id.bottomRight, "22");
        ((ImageButton) findViewById(R.id.topLeft)).setImageResource(R.mipmap.transparent);
        ((ImageButton) findViewById(R.id.topCenter)).setImageResource(R.mipmap.transparent);
        ((ImageButton) findViewById(R.id.topRight)).setImageResource(R.mipmap.transparent);
        ((ImageButton) findViewById(R.id.centerLeft)).setImageResource(R.mipmap.transparent);
        ((ImageButton) findViewById(R.id.centerCenter)).setImageResource(R.mipmap.transparent);
        ((ImageButton) findViewById(R.id.centerRight)).setImageResource(R.mipmap.transparent);
        ((ImageButton) findViewById(R.id.bottomLeft)).setImageResource(R.mipmap.transparent);
        ((ImageButton) findViewById(R.id.bottomCenter)).setImageResource(R.mipmap.transparent);
        ((ImageButton) findViewById(R.id.bottomRight)).setImageResource(R.mipmap.transparent);
    }

    // reset the board when pushed on New Game button
    public void resetButton(View view) {
        resetBoard();
        System.out.println("Board Reset");
    }

    // check which button is pushed and do the turn
    public void clickTicTac(View view) {

        System.out.println("Button Pushed");
        ImageButton pushedButton = (ImageButton) findViewById(view.getId());

        int horizontal = Integer.parseInt(map.get(view.getId()).substring(0,1));
        int vertical = Integer.parseInt(map.get(view.getId()).substring(1,2));

        System.out.println(horizontal);
        System.out.println(vertical);

        if(board[horizontal][vertical] == 0) {

            System.out.println("box was empty, now filling..");
            // Do the human turn and check for a winner
            board[horizontal][vertical] = 1;
            pushedButton.setImageResource(R.mipmap.circle);
            checkWinner(1);

            System.out.println("image resource changed");

            // do the computer move and check for a winner
            computerTurn();
            System.out.println("Done computer turn");
            checkWinner(2);
        }

    }

    // Generates a random move
    private void computerTurn() {
        Random rand = new Random();
        int h,v;

        // generate a random legal move
        do {
            h = rand.nextInt(3);
            v = rand.nextInt(3);

        } while(board[h][v] != 0);

        // if we found a legal move update the board and add a cross
        board[h][v] = 2;
        int id = -1;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().equals(h + "" + v))
                id = entry.getKey();
        }

        // Add the cross to the imageButton
        ImageButton computerButton = (ImageButton) findViewById(id);
        computerButton.setImageResource(R.mipmap.cross);
    }


    // checks if there is a winner in the current board state
    private void checkWinner(int whoToCheck) {
        String winner = whoToCheck == 1 ? "You Win!" : "Boom!";

        // check for winning states and print the winner of one is found
        if ((board[0][0] == whoToCheck && board[0][1] == whoToCheck && board[0][2] == whoToCheck) ||
            (board[1][0] == whoToCheck && board[1][1] == whoToCheck && board[1][2] == whoToCheck) ||
            (board[2][0] == whoToCheck && board[2][1] == whoToCheck && board[2][2] == whoToCheck) ||
            (board[0][0] == whoToCheck && board[1][0] == whoToCheck && board[2][0] == whoToCheck) ||
            (board[0][1] == whoToCheck && board[1][1] == whoToCheck && board[2][1] == whoToCheck) ||
            (board[0][2] == whoToCheck && board[1][2] == whoToCheck && board[2][2] == whoToCheck) ||
            (board[0][0] == whoToCheck && board[1][1] == whoToCheck && board[2][2] == whoToCheck) ||
            (board[2][0] == whoToCheck && board[1][1] == whoToCheck && board[0][2] == whoToCheck)) {
            Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
            resetBoard();
        }

    }


}
