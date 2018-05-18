package tp9.part2;

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
    TextField lastNameTextField;
    TextField firstNameTextField;
    TextField addressTextField;
    TextField birthdayTextField;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();

        lastNameTextField = new TextField();
        firstNameTextField = new TextField();
        addressTextField = new TextField();
        birthdayTextField = new TextField();

        lastNameTextField.setPromptText("nom");
        firstNameTextField.setPromptText("prenom");
        addressTextField.setPromptText("adresse");
        birthdayTextField.setPromptText("date de naissance");

        lastNameTextField.setTextFormatter(getNameValidator());
        firstNameTextField.setTextFormatter(getNameValidator());
        birthdayTextField.setTextFormatter(getBirthdayValidator());

        root.getChildren().addAll(lastNameTextField, firstNameTextField, addressTextField, birthdayTextField);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Validator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public TextFormatter<String> getNameValidator() {
        return new TextFormatter<String>(
                change -> {
                    String text = change.getText();
                    if (!text.matches("[a-zA-Z]")) {
                        return null;
                    }

                    change.setText(text.toUpperCase());
                    return change;
                }
        );
    }

    public TextFormatter<String> getBirthdayValidator() {
        return new TextFormatter<>(
                change -> {
                    int dateLength = birthdayTextField.getText().length();
                    System.out.println(dateLength);
                    String text = change.getText();
                    if ((text.length() != 0) && (
                            (dateLength == 10)
                                    || (((dateLength == 2) || (dateLength == 5)) && !text.matches("[/]"))
                                    || ((dateLength != 2) && (dateLength != 5) && !text.matches("[0-9]"))
                    )) {
                        return null;
                    }
                    return change;
                }
        );
    }

}
