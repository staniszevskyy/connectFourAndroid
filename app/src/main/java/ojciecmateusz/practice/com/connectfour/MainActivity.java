package ojciecmateusz.practice.com.connectfour;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.Console;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ImageView currPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currPlayer = (ImageView) findViewById(R.id.currPlayerImageView);
        currPlayer.setImageResource(R.drawable.red);



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
            gameEnds();
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

    public void restartGame(View v){
        Restart dialog = new Restart();
        dialog.show(getSupportFragmentManager(), "dialog2");

    }

}
