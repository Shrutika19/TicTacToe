package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    //0: Black , 1: Red , 2: Empty

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2,};
    int[][] winningPosition = {{0,1,2} , {3,4,5} , {6,7,8}, {0,4,8,} , {2,4,6} ,{0,3,6} , {1,4,7} ,{2,5,8}};
    boolean gameActive = true;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString());
        int tapCounter = Integer.parseInt(counter.getTag().toString());
        String xTap = (String.valueOf(tapCounter));
        Log.i("Tag:" , xTap);

        if(gameState[tapCounter] == 2 && gameActive) {

            gameState[tapCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blackcircle);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.redcircle);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            String winner = "";

            for (int winningPos[] : winningPosition) {
                if (gameState[winningPos[0]] == gameState[winningPos[1]] && gameState[winningPos[1]] == gameState[winningPos[2]]
                        && gameState[winningPos[0]] != 2) {


                    gameActive = false;

                    if (activePlayer == 0) {
                        winner = "Red";
                    } else {
                        winner = "Black";
                    }


                    Button playAgain = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

                    winnerText.setText(winner +" has won!");
                    winnerText.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view){

        Button playAgain = (Button) findViewById(R.id.playAgainButton);
        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);


        winnerText.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i =0 ; i<gameState.length; i++){
            gameState[i] = 2;
        }

         activePlayer = 0;
         gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}