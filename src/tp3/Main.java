package tp3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        VBox vBox = new VBox();

        Button button1 = new Button("button 1");
        Button button2 = new Button("button 2");
        Button button3 = new Button("button 3");
        Button button4 = new Button("button 4");

        button1.setId("button1");
        button2.setId("button2");
        button3.setId("button1");
        button4.setId("button2");

        vBox.getChildren().addAll(button1, button2, button3, button4);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        root.setCenter(vBox);

        primaryStage.setTitle("Exercice 3");
        Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
