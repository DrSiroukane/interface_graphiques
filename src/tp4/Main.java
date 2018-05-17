package tp4;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author Slimane SIROUKANE
 */
public class Main extends Application {
    final int height = 768;
    final int width = 1024;

    WebView webView;
    WebEngine webEngine;
    WebHistory webHistory;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane(); // main layout
        HBox navigateBar = new HBox(); // url bar layout
        StackPane stackPane = new StackPane(); // web navigation layout

        Button backButton = new Button(); // back button
        backButton.setDisable(true);
        backButton.setId("backButton");

        Button nextButton = new Button(); // next button
        nextButton.setDisable(true);
        nextButton.setId("nextButton");

        Label urlLabel = new Label("URL"); // url label

        TextField urlTextField = new TextField(); // url text field
        urlTextField.setPrefWidth((double) width * 0.8);
        urlTextField.setPromptText("http://");

        navigateBar.getChildren().addAll(backButton, nextButton, urlLabel, urlTextField);
        navigateBar.setAlignment(Pos.CENTER);
        navigateBar.setSpacing(10);
        navigateBar.setPadding(new Insets(10, 10, 10, 10));

        webView = new WebView();
        webEngine = webView.getEngine();
        webHistory = webEngine.getHistory();
        stackPane.getChildren().add(webView);

        /*
            Enter press handler on url text field
         */
        urlTextField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                String url = urlTextField.getText();
                webView.getEngine().load(url);
                // System.out.println("load " + url);
            }
        });

        /*
            load page listener
         */
        webEngine.getLoadWorker().stateProperty().addListener(
                (ov, oldState, newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        primaryStage.setTitle(webEngine.getTitle());
                        urlTextField.setText(webEngine.getDocument().getDocumentURI());
                        int history_size = webHistory.getEntries().size();
                        int current_index = webHistory.getCurrentIndex();
                        System.out.println(current_index + " - " + history_size);
                        if ((0 < webHistory.getEntries().size()) && (current_index != 0)) {
                            backButton.setDisable(false);
                        }
                    }
                }
        );

        /*
            back button on action
         */
        backButton.setOnAction(e -> {
            int history_size = webHistory.getEntries().size();
            int current_index = webHistory.getCurrentIndex();
//            System.out.println(current_index + " - " + history_size);
            if (0 < current_index) {
                webHistory.go(-1);
                nextButton.setDisable(false);
                if ((current_index - 1) == 0) {
                    backButton.setDisable(true);
                }
            }
        });

        /*
            next button on action
         */
        nextButton.setOnAction(e -> {
            int history_size = webHistory.getEntries().size();
            int current_index = webHistory.getCurrentIndex();
//            System.out.println(current_index + " - " + history_size);
            if ((current_index + 1) < history_size) {
                webHistory.go(+1);
                backButton.setDisable(false);
                if ((current_index + 2) == history_size) {
                    nextButton.setDisable(true);
                }
            }
        });

        root.setTop(navigateBar);
        root.setCenter(stackPane);

        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        /*
            change window width listener
         */
        scene.widthProperty().addListener(
                (ov, oldVal, newVal) -> {
                    urlTextField.setPrefWidth((double) newVal * 0.8);
                }
        );

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
