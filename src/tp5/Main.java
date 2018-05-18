package tp5;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

/**
 * @author Slimane Siroukane
 */
public class Main extends Application {
    final int height = 450;
    final int width = 450;

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        Shape[][] shapes = new Shape[8][8];

        Random rand = new Random();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                Shape shape = new Shape(
                        rand.nextInt(2), // random shape
                        rand.nextInt(9), // random color
                        50 // size
                );
                if(shape.isCircle()){
                    shape.setZoomValue(rand.nextFloat());
                    root.add(shape.getCircle(), i, j);
                }else if(shape.isRectangle()) {
                    shape.setRotateAngle(rand.nextInt(270));
                    root.add(shape.getRectangle(), i, j);
                }
                shape.setSpeed(2000 + rand.nextInt(1000));
                shape.setAnimation();
                shapes[i][j] = shape;
            }
        }

        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        stopButton.setDisable(true);

        startButton.setOnAction(e -> {
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++) {
                    shapes[i][j].changeStatus(true);
                }
            }

            startButton.setDisable(true);
            stopButton.setDisable(false);
        });

        stopButton.setOnAction(e -> {
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++) {
                    shapes[i][j].changeStatus(false);
                }
            }

            stopButton.setDisable(true);
            startButton.setDisable(false);
        });

        root.add(startButton, 2, 9);
        root.add(stopButton, 5, 9);

        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

