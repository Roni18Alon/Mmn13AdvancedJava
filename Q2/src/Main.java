/*
Author: Roni Alon 315565176
Main class starts the GUI of  mmn 13 q2 - 4 in lines game .
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    public void start(Stage stage) throws Exception{
        Parent root = (Parent)
                FXMLLoader.load(getClass().getResource("GameBoard.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("4 In Line Game");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
        System.out.println();
    }
}
