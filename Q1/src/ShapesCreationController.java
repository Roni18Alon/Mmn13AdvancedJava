/*
Author: Roni Alon 315565176
ShapesCreationController class controls the javaFX GUI for mmn 13 q1 - draw shapes.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class ShapesCreationController<E> {

    @FXML
    private Button undoButton;

    @FXML
    private CheckBox fillButton;

    @FXML
    private ColorPicker colorPick;

    @FXML
    private Pane pane;

    private double startX, startY, endX, endY;
    private Shape shape;

    enum Shapes {
        line,
        rectangle,
        ellipse
    }

    private Shapes chosenShape;

    //at first we can't undo until we create any shape
    public void initialize() {
        undoButton.setDisable(true);
    }

    @FXML
    void clearPane(ActionEvent event) {
        pane.getChildren().clear(); //clear the pane from all shapes
    }

    @FXML
    void createEllipse(ActionEvent event) {
        fillButton.setDisable(false); //ellipse can be filled
        chosenShape = Shapes.ellipse;
    }

    @FXML
    void createLine(ActionEvent event) {
        fillButton.setDisable(true); //line can't be filled
        chosenShape = Shapes.line;
    }

    @FXML
    void createRectangle(ActionEvent event) {
        System.out.println("Rectangle");
        fillButton.setDisable(false); //rectangle can be filled
        chosenShape = Shapes.rectangle;
    }

    @FXML //get the x,y coordinates of the press
    void mousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
    }

    @FXML
    void undoShape(ActionEvent event) {
        if (pane.getChildren().size() != 0) {
            pane.getChildren().remove(pane.getChildren().size() - 1); //remove last shape from arraylist
            if (pane.getChildren().size()==0) //if we have two shapes we will press twice
            {
                undoButton.setDisable(true);
            }
        } else {
            undoButton.setDisable(true);
        }//can't undo anymore because we undo all the shapes in pane
    }

    @FXML
    void mouseReleased(MouseEvent event) {
        endX = event.getX();
        endY = event.getY();
        if (pane.getChildren().size()==0){
            undoButton.setDisable(true);
        }
        if (endX>=0 && endY>=0 && endY<=pane.getHeight() &&endX<=pane.getWidth()){ //if the released point is outside of pane boarders - do not create shape at all
            createShape(); //create the shape with switch case
            drawShape(); //draw the chosen shape
        }
    }

    //create the shape by pressed and release coordinates with the color we chose
    private void createShape() {
        switch (chosenShape) {
            case line:
                shape = new Line(startX, startY, endX, endY);
                shape.setStroke(colorPick.getValue());
                break;
            case rectangle:
                shape = new Rectangle(startX, startY, Math.abs(endX - startX), Math.abs(endY - startY));
                break;
            case ellipse:
                double eRadius, middleX, middleY, eRadius2;
                eRadius = Math.sqrt(Math.pow((startX - endX), 2) + Math.pow((startY - endY), 2)) / 2;
                middleX = (startX + endX) / 2;
                middleY = (startY + endY) / 2;
                eRadius2 = Math.abs(middleY - endY);
                shape = new Ellipse(middleX, middleY, eRadius, eRadius2);
                break;
        }
    }

    //draw the shape and fill if we chose so
    private void drawShape() {
        if (chosenShape.equals(Shapes.line)) {
            shape.setStroke(colorPick.getValue());
        } else {
            if (fillButton.isSelected()) {
                shape.setFill(colorPick.getValue());
            } else {
                shape.setStroke(colorPick.getValue());
                shape.setFill(Color.TRANSPARENT); //only stroke lines without fill
            }
        }
        pane.getChildren().add(shape); //draw on pane
        undoButton.setDisable(false);
    }
}