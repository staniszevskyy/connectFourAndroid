package ojciecmateusz.practice.com.connectfour;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTest {

    @Test
    public void insertBluToken(){
        GameBoard board = new GameBoard(6,7);
        board.insertToken(1, 2);
        assertEquals("blue", board.getGameBoard()[5][1].getColor());
    }

    @Test
    public void insertRedToken(){
        GameBoard board = new GameBoard(6,7);
        board.insertToken(1, 1);
        assertEquals("red", board.getGameBoard()[5][1].getColor());
    }

    @Test
    public void insertUnknownToken(){
        GameBoard board = new GameBoard(6,7);
        board.insertToken(1, 1337);
        assertEquals("none", board.getGameBoard()[5][1].getColor());
    }

    @Test
    public void columnFull(){
        GameBoard board = new GameBoard(6,7);
        board.insertToken(0, 1);
        board.insertToken(0, 1);
        board.insertToken(0, 1);
        board.insertToken(0, 1);
        board.insertToken(0, 1);
        board.insertToken(0, 1);
        Boolean output = board.checkIfColumnFull(0);
        assertEquals(true, output);
    }

    @Test
    public void redWinsHorizontal(){
        GameBoard board = new GameBoard(6,7);
        board.insertToken(1, 1);
        board.insertToken(2, 1);
        board.insertToken(3, 1);
        board.insertToken(4, 1);
        Boolean output = board.checkHorizontal();
        assertEquals(true, output);
    }

    @Test
    public void redWinsVertical(){
        GameBoard board = new GameBoard(6,7);
        board.insertToken(1, 1);
        board.insertToken(1, 1);
        board.insertToken(1, 1);
        board.insertToken(1, 1);
        Boolean output = board.checkVertical();
        assertEquals(true, output);
    }

    @Test
    public void redWinsDiagonal(){
        GameBoard board = new GameBoard(6,7);
        board.insertToken(1, 1);
        board.insertToken(2, 2);
        board.insertToken(2, 1);
        board.insertToken(3, 1);
        board.insertToken(3, 2);
        board.insertToken(3, 1);
        board.insertToken(4, 2);
        board.insertToken(4, 1);
        board.insertToken(4, 2);
        board.insertToken(4, 1);
        Boolean output = board.checkDiagonal();
        assertEquals(true, output);
    }


    @Test
    public void bluWinsVertical(){
        GameBoard board = new GameBoard(6,7);
        board.insertToken(1, 2);
        board.insertToken(1, 2);
        board.insertToken(1, 2);
        board.insertToken(1, 2);
        Boolean output = board.checkVertical();
        assertEquals(true, output);
    }

    @Test
    public void bluWinsHorizontal(){
        GameBoard board = new GameBoard(6,7);
        board.insertToken(1, 2);
        board.insertToken(2, 2);
        board.insertToken(3, 2);
        board.insertToken(4, 2);
        Boolean output = board.checkHorizontal();
        assertEquals(true, output);
    }

    @Test
    public void bluWinsDiagonal(){
        GameBoard board = new GameBoard(6,7);
        board.insertToken(1, 2);
        board.insertToken(2, 1);
        board.insertToken(2, 2);
        board.insertToken(3, 2);
        board.insertToken(3, 1);
        board.insertToken(3, 2);
        board.insertToken(4, 1);
        board.insertToken(4, 2);
        board.insertToken(4, 1);
        board.insertToken(4, 2);
        Boolean output = board.checkDiagonal();
        assertEquals(true, output);
    }



}