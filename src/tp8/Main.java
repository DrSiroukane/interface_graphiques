package tp8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane(); // main layout
        Pane pane = new Pane(); // circles and rectangles space
        root.setCenter(pane);

        ToggleGroup Shapes = new ToggleGroup(); // shaps radio buttons toggle
        ToggleGroup Modes = new ToggleGroup(); // modes radio buttons toggle

        RadioButton circleRadioButton = new RadioButton("Circle");
        circleRadioButton.setSelected(true); // selected
        circleRadioButton.setToggleGroup(Shapes);

        RadioButton rectangleRadioButton = new RadioButton("Rectangle");
        rectangleRadioButton.setToggleGroup(Shapes);

        RadioButton addRadioButton = new RadioButton("Add");
        addRadioButton.setSelected(true); // selected
        addRadioButton.setToggleGroup(Modes);

        RadioButton editRadioButton = new RadioButton("Edit");
        editRadioButton.setToggleGroup(Modes);

        ColorPicker colorPicker = new ColorPicker();

        HBox hBox = new HBox();
        hBox.getChildren().addAll(circleRadioButton, rectangleRadioButton, addRadioButton, editRadioButton, colorPicker);
        hBox.setSpacing(10);

        root.setTop(hBox);

        pane.setOnMouseClicked(e -> {
            if (addRadioButton.isSelected()) {
                if (circleRadioButton.isSelected()) {
                    /**
                     * Create a Circle and fill it with a random color
                     */
                    Circle circle = new Circle(50);
                    circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                    circle.setCenterX(e.getX());
                    circle.setCenterY(e.getY());
                    circle.setOnMouseClicked(e2 -> {
                        if (editRadioButton.isSelected()) {
                            circle.setFill(colorPicker.getValue()); // fill circle by selected color on colorPicker
                        }
                    });
                    // add circle to pane
                    pane.getChildren().add(circle);
                } else if (rectangleRadioButton.isSelected()) {
                    /**
                     * Create a Rectangle and fill it with a random color
                     */
                    Rectangle rectangle = new Rectangle(100, 100);
                    rectangle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                    rectangle.setX(e.getX() - 50);
                    rectangle.setY(e.getY() - 50);
                    rectangle.setOnMouseClicked(e2 -> {
                        if (editRadioButton.isSelected()) {
                            rectangle.setFill(colorPicker.getValue()); // fill rectangle by selected color on colorPicker
                        }
                    });
                    // add circle to pane
                    pane.getChildren().add(rectangle);
                }
            }
        });

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}