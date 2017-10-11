package com.androiddev.brianrecuero.theconnectfourgame;

/**
 * Created by Brian Recuero on 10/5/2017.
 * Bugg After clicking button multiple times code crashes
 */

public class ConnectFour {
    public static final int column = 7;//Having issues registering
    public static final int row = 6;
    private int player_Turn;
    private int[][] game;

    public ConnectFour() {
        game = new int[row][column];
        resetGame();

    }

    public int play(int r, int c) {
        int currentTurn = player_Turn;
        if (r >= 0 && c >= 0 && r < row && c < column
                && game[0][c] == 0) {//........if it fits in the params and there are free spaces on the Connect Four Board
            int rows;
            for(rows = ConnectFour.row - 1; rows > -1;rows--){
                if(game[rows][c]==0) {
                    game[rows][c] = player_Turn;
                    break;
                }
            }

            if (player_Turn == 1) {
                player_Turn = 2;
            } else if(player_Turn==2) {
                player_Turn = 1;

            }
            return currentTurn;
        } else {
            return 0;//............if not a valid move Zero is returned
        }
    }

    public int whoWon() {
        int rows = checkRows();
        if (rows > 0) {
            return rows;
        }
        int columns = checkColumns();
        if (columns > 0) {
            return columns;
        }
        int diagonals = checkDiagonals();//change
        if (diagonals > 0) {
            return diagonals;

        }
        return 0;

    }

    protected int checkRows() {
        for (int r = 0; r < row; ++r) {//Must Check Rows
            int count = 0;
            for (int c = 1; c < column; ++c) {
                if ((game[r][c] != 0 && c!=row-1) &&
                        game[r][c] == game[r][c - 1]) {
                    ++count;
                } else {
                    count = 1;
                }
                if (count >= 4) {
                    return game[r][c];
                }

            }
        }
        return 0;
    }

    protected int checkColumns() {
        for (int c = 0; c < column; ++c) {
            int count = 0;

            for (int r = 1; r < row; ++r) {
                if ((game[r][c] != 0 ) &&
                        game[r][c] == game[r - 1][c]) {
                    ++count;

                } else {
                    count = 1;
                }
                if (count >= 4) {
                    return game[r][c];
                }

            }
        }
        return 0;
    }

    protected int checkDiagonals() {


        final int maxx = row;
        final int maxy = column;

        int[][] directions = {{1,0}, {1,-1}, {1,1}, {0,1}};
        for (int[] d : directions) {
            int dx = d[0];
            int dy = d[1];
            for (int x = 0; x < maxx; x++) {
                for (int y = 0; y < maxy; y++) {
                    int lastx = x + 3*dx;
                    int lasty = y + 3*dy;
                    if (0 <= lastx && lastx < maxx && 0 <= lasty && lasty < maxy) {
                        int w = game[x][y];
                        if (w != 0 && w == game[x+dx][y+dy]
                                && w == game[x+2*dx][y+2*dy]
                                && w == game[lastx][lasty]) {
                            return w;
                        }
                    }
                }
            }
        }
        return 0; // no winner
    }


    public boolean canNotPlay() {
        boolean result = true;


            for(int c=0;c<7;c++){
                if(game[0][c]==0){
                    result= false;
                }

            }


        return result;
    }
    public boolean isGameOver(){
        return canNotPlay() || (whoWon()>0);

    }
    public void resetGame(){
        for(int r=0;r<row;r++){
            for(int c=0;c<column;c++){
                game[r][c]=0;
            }
        }
        player_Turn=1;
    }
    public String result(){
        if(whoWon()>0){
            return "Player"+ whoWon() + " won";
        }
        else if (canNotPlay()){
            return "Tie Game";

        }else{
            return "Play!!";
        }


    }



}