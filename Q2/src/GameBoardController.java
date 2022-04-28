/*
Author: Roni Alon 315565176
GameBoardController class controls the javaFX GUI for mmn 13 q2 - 4 in lines game .
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;


public class GameBoardController {

    private Board b;
    private Circle circle;
    private int row, col;
    private Alert a;
    private Player current;
    private ArrayList<Circle> circleList = new ArrayList<Circle>();
    private final int FIRST_COL=1;
    private final int SECOND_COL=2;
    private final int THIRD_COL=3;
    private final int FOURTH_COL=4;
    private final int FIFTH_COL=5;
    private final int SIXTH_COL=6;
    private final int SEVEN_COL=7;


    @FXML
    private GridPane boardGrid;
    @FXML
    private Button firstButton;

    @FXML
    private Button secondButton;

    @FXML
    private Button thirdButton;

    @FXML
    private Button fourthButton;

    @FXML
    private Button fifthButton;

    @FXML
    private Button sixthButton;

    @FXML
    private Button seventhButton;

    public EventHandler<DialogEvent> initialize() {
        b = new Board();//create board
        restart();
        return null;
    }

    @FXML
    void clearBoard(ActionEvent event) {
        restart(); //when press clear - remove all circles and play again
    }

    @FXML
    void fifthCol(ActionEvent event) {
        playYourTurn(FIFTH_COL);
        fifthButton.setDisable(b.isEmpty(col));
    }

    @FXML
    void firstCol(ActionEvent event) {
        playYourTurn(FIRST_COL);
        firstButton.setDisable(b.isEmpty(col));
    }

    @FXML
    void fourthCol(ActionEvent event) {
        playYourTurn(FOURTH_COL);
        fourthButton.setDisable(b.isEmpty(col));
    }


    @FXML
    void secondCol(ActionEvent event) {
        playYourTurn(SECOND_COL);
        secondButton.setDisable(b.isEmpty(col));
    }

    @FXML
    void sevenCol(ActionEvent event) {
        playYourTurn(SEVEN_COL);
        seventhButton.setDisable(b.isEmpty(col));
    }

    @FXML
    void sixthCol(ActionEvent event) {
        playYourTurn(SIXTH_COL);
        sixthButton.setDisable(b.isEmpty(col));
    }

    @FXML
    void thirdCol(ActionEvent event) {
        playYourTurn(THIRD_COL);
        thirdButton.setDisable(b.isEmpty(col));
    }

   //create circle shape
    public void createShape(int row, int col, Color c) {
        circle = new Circle((boardGrid.getHeight() / 14) - 1, c);
        GridPane.setHalignment(circle, javafx.geometry.HPos.CENTER);
        boardGrid.add(circle, col, row);
        circleList.add(circle);
    }

    //play your turn
    private void playYourTurn(int chosenCol) {
        col = chosenCol - 1; //gets a number and refactor to index
        row = b.emptySpace(col); //check the next empty cell in this column
        if (row!=Integer.MIN_VALUE) {
            current = b.playTurn(row, col);
            createShape(row, col, current.getPlayerColor());
            if (b.isWinner(current)) {
                alertWinner(current);
            }
        }
    }

    //if we found a winner raise an alert
    private void alertWinner(Player current) {
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("WINNER OF THE GAME");
        a.setHeaderText("winner");
        a.setContentText("The winner is player number " + current.getPlayerId());
        a.showAndWait();
        a.setOnCloseRequest(initialize());
    }

   //restart the board - clear all circles we draw
    public void restart() {
        boardGrid.getChildren().removeAll(circleList);
        if (circleList !=null) circleList.clear();
        b.clearLogicalMatrix();//clear logical board
        setEnable();
    }

    public void setEnable() {
        firstButton.setDisable(false);
        secondButton.setDisable(false);
        thirdButton.setDisable(false);
        fourthButton.setDisable(false);
        fifthButton.setDisable(false);
        sixthButton.setDisable(false);
        seventhButton.setDisable(false);
    }
}
