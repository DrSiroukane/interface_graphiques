package tp6.images_version;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {
    public ArrayList<Carte> cartes;
    public int opened_carte;
    public int couple_solved;
    public boolean begin_game;
    public boolean end_game;
    public long game_time;
    public Scene scene;

    @Override
    public void start(Stage primaryStage) {
        String[] urls = {
                "images/A.png",
                "images/2.png",
                "images/3.png",
                "images/4.png",
                "images/5.png",
                "images/6.png",
                "images/7.png",
                "images/8.png"
        };

        GridPane root = new GridPane();
        cartes = new ArrayList<>();
        opened_carte = 0;
        couple_solved = 0;
        ArrayList<Integer> opened_cartes_id = new ArrayList<>();
        begin_game = true;
        end_game = false;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2)));

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<8; i++){
            list.add(i);
            list.add(i);
        }
        Collections.shuffle(list);

        for(int i=0; i<16; i++){
            Carte carte = new Carte(i, urls[list.get(i)]);
            carte.getButton().setOnAction(e -> {
                if((opened_carte < 2) && !carte.open){
                    timeline.stop();
                    carte.openButton();
                    opened_cartes_id.add(carte.id);
                    opened_carte++;
                }else if((0 < opened_carte) && carte.open){
                    carte.closeButton();
                    opened_cartes_id.remove(new Integer(carte.id));
                    opened_carte--;
                }

                if(opened_carte == 2){
                    if(Carte.checkCartes(
                            cartes.get(opened_cartes_id.get(0)),
                            cartes.get(opened_cartes_id.get(1))
                    )){
                        cartes.get(opened_cartes_id.get(0)).disableButton();
                        cartes.get(opened_cartes_id.get(1)).disableButton();
                        opened_cartes_id.clear();

                        opened_carte = 0;
                        couple_solved++;
                        if(couple_solved == 8){
                            end_game = true;
                            game_time = System.currentTimeMillis() - game_time;
                            BorderPane borderPane = new BorderPane();
                            Label label = new Label("You win in " + game_time + " ms");
                            borderPane.setCenter(label);
                            scene = new Scene(borderPane);
                            primaryStage.setScene(scene);
                        }
                    }else{
                        timeline.play();
                        timeline.setOnFinished(evt -> {
                            if(opened_carte == 2){
                                cartes.get(opened_cartes_id.get(0)).closeButton();
                                cartes.get(opened_cartes_id.get(1)).closeButton();
                                opened_cartes_id.clear();
                                opened_carte = 0;
                            }
                        });
                    }
                }

                System.out.println(opened_carte);
            });
            cartes.add(carte);
        }

        int k = 0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                root.add(cartes.get(k++).getButton(), i, j);
            }
        }

        if(begin_game){
            begin_game = false;
            game_time = System.currentTimeMillis();
        }

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
