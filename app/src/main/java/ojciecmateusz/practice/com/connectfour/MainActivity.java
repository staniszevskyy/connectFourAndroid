package ojciecmateusz.practice.com.connectfour;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Console;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ImageView currPlayer;
    private Boolean bo3;
    private TextView scoreFirstTextView;
    private TextView scoreSecondTextView;
    private int scoreFirst;
    private int scoreSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bo3 = getIntent().getBooleanExtra("bo3", false);

        currPlayer = (ImageView) findViewById(R.id.currPlayerImageView);
        currPlayer.setImageResource(R.drawable.red);

        if (bo3 == true){
            TextView firstPlayerTextView = (TextView) findViewById(R.id.firstPlayerTextView);
            TextView secondPlayerTextView = (TextView) findViewById(R.id.secondPlayerTextView);
            scoreFirstTextView = (TextView) findViewById(R.id.scoreFirst);
            scoreSecondTextView = (TextView) findViewById(R.id.scoreSecond);
            firstPlayerTextView.setVisibility(View.VISIBLE);
            secondPlayerTextView.setVisibility(View.VISIBLE);
            scoreFirstTextView.setVisibility(View.VISIBLE);
            scoreSecondTextView.setVisibility(View.VISIBLE);

            scoreFirst = getIntent().getIntExtra("score1", 0);
            scoreSecond = getIntent().getIntExtra("score2", 0);
            int decrement = getIntent().getIntExtra("decrement", 0);
            Boolean fullReset = getIntent().getBooleanExtra("fullReset", false);
            if (decrement!=0) {
                if (decrement == 1) scoreFirst--;
                else scoreSecond--;
            }
            if (fullReset){
                scoreFirst=0;
                scoreSecond=0;
            }
            getIntent().removeExtra("decrement");
            getIntent().removeExtra("fullReset");
            scoreFirstTextView.setText(String.valueOf(scoreFirst));
            scoreSecondTextView.setText(String.valueOf(scoreSecond));

            }
        }






    private GameBoard board = new GameBoard(6, 7);
    private int playerID = 1;
    private int counter = board.getM() * board.getN();



    public void refreshBoard(){
        Field[][] gameBoard = board.getGameBoard();
        for (int i= 0; i<gameBoard.length; i++){
            for (int j=0; j<gameBoard[i].length; j++)
            {
                if (gameBoard[i][j].getColor().equals("red")){
                    String fieldName = "view"+j+i;
                    int resID = getResources().getIdentifier(fieldName, "id", getPackageName());
                    ImageView view = (ImageView) findViewById(resID);
                    view.setImageResource(R.drawable.red);
                    view.setVisibility(View.VISIBLE);
                }
                else if (gameBoard[i][j].getColor().equals("blue")){
                    String fieldName = "view"+j+i;
                    int resID = getResources().getIdentifier(fieldName, "id", getPackageName());
                    ImageView view = (ImageView) findViewById(resID);
                    view.setImageResource(R.drawable.blue);
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

        counter--;
        if (board.checkIfGameEnds()){
            if (bo3 == false )
                gameEnds();
            else{


                    if (playerID==1) {
                        scoreFirst++;
                        scoreFirstTextView.setText(String.valueOf(scoreFirst));
                    }
                    else {
                        scoreSecond++;
                        scoreSecondTextView.setText(String.valueOf(scoreSecond));
                    }


                    if (scoreFirst == 3 || scoreSecond == 3){
                        gameEndsBO3();
                    }
                    else {
                        Intent intent = getIntent();
                        intent.putExtra("score1", scoreFirst);
                        intent.putExtra("score2", scoreSecond);
                        finish();
                        startActivity(intent);
                    }
            }

        }

        if (playerID == 1 ){
            playerID = 2;
            currPlayer.setImageResource(R.drawable.blue);
        }
        else{
            playerID = 1;
            currPlayer.setImageResource(R.drawable.red);
        }
    }

    public void clickedColumn (View v){
        Button btn = (Button) v;
        String colNum = btn.getResources().getResourceName(btn.getId());
        colNum = colNum.split("/")[1];
        int column = Integer.parseInt(colNum.substring(9));

        if (board.checkIfColumnFull(column)){
            columnFull();

        }
        else {

            board.insertToken(column, playerID);
            refreshBoard();
        }
    }
    public void columnFull(){
        ColumnFull dialog = new ColumnFull();
        dialog.show(getSupportFragmentManager(), "dialog");
    }


    public void gameEnds() {
        GameEnds dialog = new GameEnds();
        Bundle args = new Bundle();
        args.putInt("num", playerID);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "dialog1");

    }

    public void gameEndsBO3(){
        GameEnds dialog = new GameEnds();
        Bundle args = new Bundle();
        if (scoreFirst > scoreSecond)
            args.putInt("num", 1);
        else
            args.putInt("num", 2);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "dialog1");

    }

    public void restartGame(View v){
        if (!bo3) {
            Restart dialog = new Restart();
            dialog.show(getSupportFragmentManager(), "dialog2");
        }
        else{


            RestartBO3 dialog = new RestartBO3();
            Bundle args = new Bundle();
            args.putInt("num", playerID);
            dialog.setArguments(args);
            dialog.show(getSupportFragmentManager(), "dialogreset");
        }

    }

}
