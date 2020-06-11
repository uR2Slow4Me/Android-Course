package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] positions;

    private boolean isPlayer1Turn = true;

    private int roundsCount = 0;

    private int player1Points = 0;

    private int player2Points = 0;

    private TextView player1TextView;

    private TextView player2TextView;

    public MainActivity() {
        positions = new Button[3][3];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1TextView = findViewById(R.id.text_view_player1);
        player2TextView = findViewById(R.id.text_view_player2);

        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                String buttonID = "button_" + i + j;
                int viewID = getResources().getIdentifier(buttonID, "id", getPackageName());
                positions[i][j] = findViewById(viewID);
                positions[i][j].setOnClickListener(this);
            }
        }

        final Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1Points = 0;
                player2Points = 0;
                refreshBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button position = (Button) v;

        if(!position.getText().toString().equals(""))
        {
            return;
        }

        if(isPlayer1Turn){
            position.setText("X");
        }
        else {
            position.setText("O");
        }

        roundsCount++;
        isPlayer1Turn = !isPlayer1Turn;

        if(isCurrentPlayerWinning())
        {
            win();
        }
        else if (roundsCount == 9){
            draw();
        }
    }

    private boolean isCurrentPlayerWinning(){

        String[][] fields = new String[3][3];

        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                fields[i][j] = positions[i][j].getText().toString();
            }
        }

        for (int i=0; i<3; i++){

            if(fields[i][0].equals(fields[i][1])
            && fields[i][0].equals(fields[i][2])
            && !fields[i][0].equals("")){
                return true;
            }

            if(fields[0][i].equals(fields[1][i])
            && fields[0][i].equals(fields[2][i])
            && !fields[0][i].equals("")){
                return true;
            }
        }

        if(fields[0][0].equals(fields[1][1])
                && fields[0][0].equals(fields[2][2])
                && !fields[0][0].equals("")){
            return true;
        }

        if(fields[0][2].equals(fields[1][1])
                && fields[0][2].equals(fields[2][0])
                && !fields[0][2].equals("")){
            return true;
        }

        return false;
    }

    private void win(){
        String winningText;

        if(isPlayer1Turn){
            player2Points++;
            winningText = "Player 2 wins!";
        }
        else{
            player1Points++;
            winningText = "Player 1 wins!";
        }

        Toast.makeText(this, winningText, Toast.LENGTH_SHORT).show();
        refreshBoard();
    }

    private void draw()
    {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        refreshBoard();
    }

    private void refreshBoard()
    {
        player1TextView.setText("Player 1 : " + player1Points);

        player2TextView.setText("Player 2 : " + player2Points);

        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                positions[i][j].setText("");
            }
        }

        roundsCount = 0;

        isPlayer1Turn = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundsCount", roundsCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("isPlayer1Turn", isPlayer1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundsCount = savedInstanceState.getInt("roundsCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        isPlayer1Turn = savedInstanceState.getBoolean("isPlayer1Turn");
    }
}
