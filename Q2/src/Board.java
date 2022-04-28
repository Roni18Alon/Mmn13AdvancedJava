/*
Author: Roni Alon 315565176
Board class handles the logic of 4 in line game, with a logical matrix, check if someone is the winner,anr ext for mmn 13 q2 - 4 in lines game .
 */

import javafx.scene.paint.Color;

public class Board {

    private final int[][] logicalBoard;
    private final int ROW = 6;
    private final int COL = 7;
    private final int FIRST_PLAYER=1;
    private final int SECOND_PLAYER=2;
    private final int EMPTY_CELL=0;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Player currentPlayer;

    //constructor
    public Board() {
        this.logicalBoard = new int[ROW][COL]; //filled with zero - "empty"
        this.firstPlayer = new Player(FIRST_PLAYER, Color.RED);
        this.secondPlayer = new Player(SECOND_PLAYER, Color.BLUE);
        this.currentPlayer = firstPlayer;
    }


   //get your turn and place your player id in the next optional cell of chosen column
    public Player playTurn(int row, int col) {
        // fill the right cell in logical board in the id of this player
        logicalBoard[row][col] = this.currentPlayer.getPlayerId();
        Player p = currentPlayer;
        swapPlayer();
        return p;

    }

   //Check and returns the next empty cell in given column if we can't find return min val of integer
    public int emptySpace(int col) {
        int cell = -1;
        for (int i = ROW - 1; i >= 0; i--) {
            if (logicalBoard[i][col] == EMPTY_CELL)
                return i;
        }
        return Integer.MIN_VALUE;
    }

    //swap between players
    public void swapPlayer() {
        if (currentPlayer.getPlayerId() == firstPlayer.getPlayerId()) {
            currentPlayer = secondPlayer;
        } else {
            currentPlayer = firstPlayer;
        }
    }

    //check if we have space to create more circles in this column
    public boolean isEmpty(int col) {
        return logicalBoard[0][col] != EMPTY_CELL;
    }


    public boolean isWinner(Player player) {
        //check for 4 across
        for (int row = 0; row < logicalBoard.length; row++) {
            for (int col = 0; col < logicalBoard[0].length - 3; col++) {
                if (logicalBoard[row][col] == player.getPlayerId() &&
                        logicalBoard[row][col + 1] == player.getPlayerId() &&
                        logicalBoard[row][col + 2] == player.getPlayerId() &&
                        logicalBoard[row][col + 3] == player.getPlayerId()) {
                    return true;
                }
            }
        }
        //check for 4 up and down
        for (int row = 0; row < logicalBoard.length - 3; row++) {
            for (int col = 0; col < logicalBoard[0].length; col++) {
                if (logicalBoard[row][col] == player.getPlayerId() &&
                        logicalBoard[row + 1][col] == player.getPlayerId() &&
                        logicalBoard[row + 2][col] == player.getPlayerId() &&
                        logicalBoard[row + 3][col] == player.getPlayerId()) {
                    return true;
                }
            }
        }
        //check upward diagonal
        for (int row = 3; row < logicalBoard.length; row++) {
            for (int col = 0; col < logicalBoard[0].length - 3; col++) {
                if (logicalBoard[row][col] == player.getPlayerId() &&
                        logicalBoard[row - 1][col + 1] == player.getPlayerId() &&
                        logicalBoard[row - 2][col + 2] == player.getPlayerId() &&
                        logicalBoard[row - 3][col + 3] == player.getPlayerId()) {
                    return true;
                }
            }
        }
        //check downward diagonal
        for (int row = 0; row < logicalBoard.length - 3; row++) {
            for (int col = 0; col < logicalBoard[0].length - 3; col++) {
                if (logicalBoard[row][col] == player.getPlayerId() &&
                        logicalBoard[row + 1][col + 1] == player.getPlayerId() &&
                        logicalBoard[row + 2][col + 2] == player.getPlayerId() &&
                        logicalBoard[row + 3][col + 3] == player.getPlayerId()) {
                    return true;
                }
            }
        }
        return false;
    }

    //Clear the logical board by initialize all values to 0
    public void clearLogicalMatrix(){
       if (logicalBoard!=null){
           for(int i=0;i<logicalBoard.length;i++){
               for(int j=0;j<logicalBoard[0].length;j++)
               {
                   logicalBoard[i][j]=EMPTY_CELL;
               }
           }
       }
    }

}

