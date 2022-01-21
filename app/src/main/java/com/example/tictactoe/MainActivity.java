package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Make the variable for playerScore and who is winning
    private TextView playerOneScore, playerTwoScore, playerStatus;
    // Make array for each of the 9 boxes
    private Button[] buttons = new Button[9];
    // Number of moves each game
    private int  moveCounter;
    // Variable for the game state
    //private Button resetGame;
    private int playerOneWins, playerTwoWins;
    // true = player1
    //false = player2
    boolean playerturn;
    private final int MAX_MOVES = 9;
    // p1 = 0
    // p2 = 1
    // empty = 2
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8}, // rows
            {0,3,6}, {1,4,7}, {2,5,8}, // columns
            {0,4,8}, {2,4,6}}; // diagonals

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Link all Views to variables
        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);

        for(int i=0; i< buttons.length; i++){
            String buttonID = "button" + i;
            //turn buttonID into a usable values at above (R.id.__)
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            // To register a button press
            buttons[i].setOnClickListener(this);
        }
        moveCounter = 0;
        playerOneWins = 0;
        playerTwoWins = 0;
        playerturn = true;

    }

    @Override
    public void onClick(View view) {
        // Set button so that it can only be pressed once
        if(!((Button) view).getText().toString().equals("")){
            return;
        }
        // Get the button that was just pressed
        String buttonID = view.getResources().getResourceEntryName(view.getId()); // button2
        // Trim the string so we get the button number (button2 -> 2) which we will use to
        // keep track in the gameState
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1, buttonID.length())); // 2

        // If it was player 1s turn and they pressed the button
        if (playerturn){
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#71BEF2"));
            gameState[gameStatePointer] = 0;
        }

        // If it was player 2s turn and they pressed the button
        else{
            ((Button) view).setText("O");
            ((Button) view).setTextColor(Color.parseColor("#ffffff"));
            gameState[gameStatePointer] = 1;
        }
        moveCounter++;

        if(winChecker()){
            if (playerturn){
                playerOneWins++;
                updatePlayerScore();
                Toast.makeText(this, "Player One Won", Toast.LENGTH_SHORT).show();
                replay();
            }
            else{
                playerTwoWins++;
                updatePlayerScore();
                Toast.makeText(this, "Player Two Won", Toast.LENGTH_SHORT).show();
                replay();
            }
        }
        else if (moveCounter == MAX_MOVES){
            replay();
            playerStatus.setText("Draw");
            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();

        }
        else{
            // Switch player
            playerturn = !playerturn;
        }

        // Display who is winning
        if (playerOneWins > playerTwoWins){
            int Scorediff = playerOneWins - playerTwoWins;
            String pointPlural = "";
            if (playerOneWins - playerTwoWins == 1){
                pointPlural = "Point";
            }
            else{
                pointPlural = "Points";
            }
            String message = "Player One: Winning by " + Scorediff + " " + pointPlural;
            playerStatus.setText(message);
        }
        else if (playerOneWins < playerTwoWins){
            int Scorediff = playerTwoWins - playerOneWins;
            String pointPlural = "";
            if (playerTwoWins - playerOneWins == 1){
                pointPlural = "Point";
            }
            else{
                pointPlural = "Points";
            }
            String message = "Player Two: Winning by " + Scorediff + " " + pointPlural;
            playerStatus.setText(message);
        }
        else{
            playerStatus.setText("Equal Points");

        }
    }

    public void resetGame(View v){
        moveCounter = 0;
        playerOneWins = 0;
        playerTwoWins = 0;
        updatePlayerScore();
        playerStatus.setText("Game One");
        playerturn = true;

        for (int i = 0; i < buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }

    // Check if a player has won
    public boolean winChecker(){
        boolean isWin = false;
        // Compare the win positions and check if we have the same value (X or O) in gamestate
        for(int[] winPos :winningPositions){
           if (gameState[winPos[0]] == gameState[winPos[1]] &&
                   gameState[winPos[1]] == gameState[winPos[2]] &&
                   gameState[winPos[2]] != 2) {
               isWin = true;
           }
        }
        return isWin;
    }

    //Create function to update player score
    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneWins));
        playerTwoScore.setText(Integer.toString(playerTwoWins));
    }

    //function to handle win state
    public void replay(){
        moveCounter = 0;
        playerturn = true;

        for (int i = 0; i < buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }

    public void mainLaunch(View v){
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);

    }
}