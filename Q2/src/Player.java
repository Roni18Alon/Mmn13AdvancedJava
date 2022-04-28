/*
Author: Roni Alon 315565176
Player class builds a player object that will play in mmn 13 q2 - 4 in lines game .
 */
import javafx.scene.paint.Color;

public class Player {

    private int playerId;
    private Color playerColor;

    public Player(int id, Color color){
        playerId=id;
        playerColor=color;
    }


    public int getPlayerId() {
        return playerId;
    }
    public Color getPlayerColor(){
        return playerColor;
    }

}
