package com.example.connect3v30;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0 ;
    boolean gameActive = true;
    // 2 = not used
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions ={
            {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,6,8},{0,4,8},{2,4,6}
    };


    int turn = 0;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        // 0 = x , 1 = o
        if(gameState[tappedCounter]== 2  && gameActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            turn++;
        }
        else{
            Toast.makeText(MainActivity.this, "try another place!", Toast.LENGTH_SHORT).show();
        }
        for(int[] winningPosition: winningPositions){

            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] !=2){
                // someone Won
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMasText);
                String winner = "leo";
                if(gameState[winningPosition[0]] == 0 ) {
                    winner = "carm";
                }
                winnerMessage.setText(winner+" Has Won!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.winnerMas);
                layout.animate().alpha(0);
                layout.animate().alpha(1).setDuration(500);
                layout.setVisibility(View.VISIBLE);
                gameActive = false;
                break;
            }
            else{

                    if(turn == 9){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMasText);
                        winnerMessage.setText("it's a draw !");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.winnerMas);
                        layout.animate().alpha(0);
                        layout.animate().alpha(1).setDuration(500);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }

    public void playAgain(View view){
        gameActive = true;
        turn = 0 ;
        LinearLayout layout = (LinearLayout) findViewById(R.id.winnerMas);
        layout.setVisibility(View.INVISIBLE);
        int activePlayer = 0 ;
        for(int i =0 ; i < gameState.length ; i++){
            gameState[i] = 2;
        }
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i = 0 ; i < gridLayout.getChildCount() ;i++){
            (  (ImageView) gridLayout.getChildAt(i) ).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}