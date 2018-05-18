package tp6.numbers_version;

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

/**
 * @author Slimane Siroukane
 */
public class Main extends Application {
    public ArrayList<Carte> cartes; // list of cards
    public int opened_carte; // number of opened cards
    public int couple_solved; // number of couple solved
    public boolean begin_game;
    public boolean end_game;
    public long game_time; // game time from begin to the end
    public Scene scene;

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        cartes = new ArrayList<>();
        opened_carte = 0;
        couple_solved = 0;
        ArrayList<Integer> opened_cartes_id = new ArrayList<>();
        begin_game = true;
        end_game = false;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2))); // timeline to use to flip cards

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
            list.add(i);
        }
        Collections.shuffle(list); // scramble the cards list

        for (int i = 0; i < 16; i++) {
            Carte carte = new Carte(i, list.get(i));
            carte.getButton().setOnAction(e -> {
                // open clicked card if there is less then 2 cards open and the current card is still not hidden
                if ((opened_carte < 2) && !carte.open) {
                    carte.openButton();
                    opened_cartes_id.add(carte.id);
                    opened_carte++;
                } else if ((0 < opened_carte) && carte.open) { // hide clicked card if its open
                    timeline.stop(); // stop time
                    carte.closeButton();
                    opened_cartes_id.remove(new Integer(carte.id));
                    opened_carte--;
                }

                if (opened_carte == 2) { // check if 2 cards are opened
                    if (Carte.checkCartes(
                            cartes.get(opened_cartes_id.get(0)),
                            cartes.get(opened_cartes_id.get(1))
                    )) { // if the two cards are the same
                        cartes.get(opened_cartes_id.get(0)).disableButton(); // disable first card
                        cartes.get(opened_cartes_id.get(1)).disableButton(); // disable second card
                        opened_cartes_id.clear(); // clear open card list

                        opened_carte = 0;
                        couple_solved++;
                        if (couple_solved == 8) { // if 8 couple are solved then end game
                            end_game = true;
                            game_time = System.currentTimeMillis() - game_time; // calculate game duration from begin until the end
                            BorderPane borderPane = new BorderPane();
                            Label label = new Label("You win in " + game_time + " ms");
                            borderPane.setCenter(label);
                            scene = new Scene(borderPane);
                            primaryStage.setScene(scene); // change appearing game scene to result scene
                        }
                    } else {
                        timeline.play(); // play timeline
                        timeline.setOnFinished(evt -> { // duration of 2 second finished
                            if (opened_carte == 2) { // if still 2 card open close them
                                cartes.get(opened_cartes_id.get(0)).closeButton();
                                cartes.get(opened_cartes_id.get(1)).closeButton();
                                opened_cartes_id.clear();
                                opened_carte = 0;
                            }
                        });
                    }
                }
            });
            cartes.add(carte); // add card to the cards list
        }

        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                root.add(cartes.get(k++).getButton(), i, j); // add cards button to the root layout
            }
        }

        if (begin_game) { // check the begin of game
            begin_game = false;
            game_time = System.currentTimeMillis(); // pick current start time
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
