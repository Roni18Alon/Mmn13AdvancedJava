/*
Author: Roni Alon 315565176
ShapesCreation class starts the javaFX GUI for mmn 13 q1 - draw shapes.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ShapesCreation extends Application{
    public void start(Stage stage) throws Exception{
        Parent root = (Parent)
                FXMLLoader.load(getClass().getResource("ShapesCreation.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("ShapesCreation");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
        System.out.println();
    }
}