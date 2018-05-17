package tp1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Slimane Siroukane
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane(); // create root layout
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Color.web("#383838"), null, null))); // set background
        pane.setMinWidth(300); //
        pane.setMaxWidth(900); //
        pane.setMinHeight(200); //
        pane.setMaxHeight(500); //

        ImageView imageView = new ImageView(new Image(getClass().getResource("image.jpg").toExternalForm()));
        pane.setCenter(imageView);
        root.setCenter(pane);

        Scene scene = new Scene(root, 800, 400); // inject root layout to scene
        primaryStage.setMinWidth(200); // min width
        primaryStage.setMaxWidth(1200); // max width
        primaryStage.setMinHeight(100); // min height
        primaryStage.setMaxHeight(600); // max height
        primaryStage.setTitle("Execice 1"); // set title
        primaryStage.setScene(scene); // inject scene

        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double) newValue;
                if ((300 < width) && (width < 900)) {
                    imageView.setFitWidth(width);
                }
            }
        });
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double height = (double) newValue;
                if ((200 < height) && (height < 500)) {
                    imageView.setFitHeight(height);
                }
            }
        });
        primaryStage.show(); // show stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}