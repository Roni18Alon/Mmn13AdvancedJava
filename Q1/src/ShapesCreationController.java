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

import java.util.ArrayList;

import static java.lang.Math.abs;

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
    private boolean fillShape;
    private ArrayList<E> shapesList = new ArrayList<E>();
    private Shape shape;

    enum Shapes {
        line,
        rectangle,
        ellipse
    }

    private Shapes chosenShape;


    @FXML
    void clearPane(ActionEvent event) {
        System.out.println("clear");
        pane.getChildren().clear();
    }

    @FXML
    void createEllipse(ActionEvent event) {
        System.out.println("ellipse");
        fillButton.setDisable(false);
        chosenShape = Shapes.ellipse;
    }

    @FXML
    void createLine(ActionEvent event) {
        System.out.println("line");
        fillButton.setDisable(true); //line can't be filled
        chosenShape = Shapes.line;
    }

    @FXML
    void createRectangle(ActionEvent event) {
        System.out.println("Rectangle");
        fillButton.setDisable(false);
        chosenShape = Shapes.rectangle;
    }

    @FXML
    void fillShape(ActionEvent event) {
        fillShape = fillButton.isSelected();
    }

    @FXML
    void mousePressed(MouseEvent event) {
        System.out.println("pressed");
        startX = event.getX();
        startY = event.getY();

    }

    @FXML
    void undoShape(ActionEvent event) {
        System.out.println("undo");
        if (shapesList.size() != 0) {
            shapesList.remove(shapesList.size() - 1); //remove last shape from arraylist
            pane.getChildren().remove(pane.getChildren().size() - 1);
        } else {
            undoButton.setDisable(true);
        }//can't undo anymore because we undo all the shapes in pane
    }

    @FXML
    void mouseReleased(MouseEvent event) {
        endX = event.getX();
        endY = event.getY();
        System.out.println("create");
        switch (chosenShape) {
            case line:
                System.out.println("draw line");
                shape = new Line(startX, startY, endX, endY);
                shape.setStroke(colorPick.getValue());
                break;
            case rectangle:
                System.out.println("draw rectangle");
                shape = new Rectangle(startX, startY, abs(endX - startX), abs(endY - startY));
                break;
            case ellipse:
                System.out.println("draw ellipse");
                double eRadius, middleX, middleY, eRadius2;
                eRadius = Math.sqrt(Math.pow((startX - endX), 2) + Math.pow((startY - endY), 2)) / 2;
                middleX = (startX + endX) / 2;
                middleY = (startY + endY) / 2;
                eRadius2 = abs(middleY - endY);
                shape = new Ellipse(middleX, middleY, eRadius, eRadius2);
                break;
        }
        drawShape();
    }

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
        pane.getChildren().add(shape);
        shapesList.add((E) shape);
        System.out.println("shape size :" + shapesList.size());
    }
}