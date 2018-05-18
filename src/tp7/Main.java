package tp7;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane(); // main layout

        HBox hBox = new HBox(); // left layout (tools)
        StackPane pane = new StackPane(); // center layout (circle zoon)

        Slider slider = new Slider(); // slider to control
        slider.setMax(200); // set max value (default one is equal to 100)
        slider.setShowTickMarks(true); // show marks
        slider.setShowTickLabels(true); // show label of (x * unit)
        slider.setMajorTickUnit(50); // set unit value
        slider.setOrientation(Orientation.VERTICAL); // set orientation

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.GREEN); // set value to green

        hBox.getChildren().addAll(slider, colorPicker);
        root.setLeft(hBox);

        Circle circle = new Circle();
        circle.setId("circle");

        pane.getChildren().addAll(circle);
        pane.setId("pane");

        root.setCenter(pane);

        /**
         * slider listener to change circle diameter
         */
        slider.valueProperty().addListener(
                (ov, oldVal, newVal) -> {
                    circle.setRadius(newVal.doubleValue() / 2);
                }
        );

        // change diameter using bind
//        circle.radiusProperty().bind(slider.valueProperty().divide(2));

        /**
         * color picker listener to change circle border color to the new value
         */
        colorPicker.valueProperty().addListener(
                (ov, oldVal, newVal) -> {
                    circle.setStroke(newVal);
                }
        );


        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
