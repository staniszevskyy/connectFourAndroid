package ojciecmateusz.practice.com.connectfour;

public class GameBoard {

    private Field[][] gameBoard;
    private int n;
    private int m;
    public GameBoard(int n, int m) {


        gameBoard = new Field[n][m];
        this.n = n;
        this.m = m;
        for (int i= 0; i<gameBoard.length; i++){
            for (int j=0; j<gameBoard[i].length; j++)
                gameBoard[i][j] = new Field();
        }

    }

    public Boolean checkVertical(){

        for (int j=0; j< m; j++){ // 7
            int count = 0;

            for (int i=1; i<n; i++){ // 6
                String tempColor = gameBoard[i-1][j].getColor();
                if (tempColor.equals(gameBoard[i][j].getColor()) && !tempColor.equals("none"))
                    count++;
                else {
                    count = 0;

                }
                if (count == 3) return true;
            }
        }
        return false;
    }


    public Boolean checkHorizontal(){

        for (int j=0; j< n; j++){
            int count = 0;
            for (int i=1 ; i< m; i++){
                String tempColor = gameBoard[j][i-1].getColor();
                if (tempColor.equals(gameBoard[j][i].getColor()) && !tempColor.equals("none"))
                    count ++;
                else{
                    count = 0;

                }
                if (count==3) return true;
            }
        }
        return false;
    }

    public Boolean checkDiagonal(){
        for (int i= 0; i < n - 3 ; i++){
            for (int j = 0 ; j < m-3; j++){
                if (gameBoard[i][j].getColor().equals(gameBoard[i+1][j+1].getColor()) &&
                        gameBoard[i+1][j+1].getColor().equals(gameBoard[i+2][j+2].getColor()) &&
                        gameBoard[i+2][j+2].getColor().equals(gameBoard[i+3][j+3].getColor()) &&
                        !gameBoard[i][j].getColor().equals("none"))
                    return true;
            }
        }

        for (int i= 0; i < n-3 ; i++){
            for (int j = m - 1 ; j > 2; j--){
                if (gameBoard[i][j].getColor().equals(gameBoard[i+1][j-1].getColor()) &&
                        gameBoard[i+1][j-1].getColor().equals(gameBoard[i+2][j-2].getColor()) &&
                        gameBoard[i+2][j-2].getColor().equals(gameBoard[i+3][j-3].getColor()) &&
                        !gameBoard[i][j].getColor().equals("none"))
                    return true;
            }
        }

        return false;
    }

    public Boolean checkIfGameEnds(){
        if (checkHorizontal() || checkVertical() || checkDiagonal())
            return true;
        return false;
    }

    public Boolean checkIfColumnFull(int column){
        if (gameBoard[0][column].getColor().equals("none")) return false;
        return true;

    }

    public void insertToken(int colNum, int playerID){

        for (int row = gameBoard.length-1; row>=0; row--){
            if (gameBoard[row][colNum].getColor().equals("none")) {
                if (playerID == 1)
                    gameBoard[row][colNum].setColor("red");
                else
                    gameBoard[row][colNum].setColor("blue");
                break;
            }
        }
    }

    @Override
    public String toString() {
        for (int i = 0 ; i < gameBoard.length; i++){
            for (int j = 0 ; j <gameBoard[i].length; j++){
                System.out.print(gameBoard[i][j].getColor()+ " ");
            }
            System.out.println();
        }
        return "";
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public Field[][] getGameBoard() {
        return gameBoard;
    }
}
