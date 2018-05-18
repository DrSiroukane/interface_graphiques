package tp9.part1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Slimane Siroukane
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        TextField typeTextField = new TextField();
        typeTextField.setPromptText("Type...");
        TextField secondTextField = new TextField();

        TextFormatter<String> uppercaseTextFormatter = new TextFormatter<String>(
                change -> {
                    String text = change.getText();
                    if ((0 != text.length()) && !text.matches("[a-zA-Z]") ) {
                        return null;
                    }
                    change.setText(text.toUpperCase());
                    return change;
                }
        );

        TextFormatter<String> voyelleTextFormatter = new TextFormatter<String>(
                change -> {
                    String text = change.getText();
                    if (text.matches("[a,e,i,o,u,y,A,E,I,O,U,Y]")) {
                        return null;
                    }
                    change.setText(text.toUpperCase());
                    return change;
                }
        );

        typeTextField.setTextFormatter(uppercaseTextFormatter);
        secondTextField.setTextFormatter(voyelleTextFormatter);

        root.getChildren().addAll(typeTextField, secondTextField);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
