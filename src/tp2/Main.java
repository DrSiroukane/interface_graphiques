package tp2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

/**
 * @author Slimane Siroukane
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane(); // layout
        MenuBar menuBar = new MenuBar(); // bar menu

        // file menu
        Menu fileMenu = new Menu("File"); // file menu
        MenuItem openItem = new MenuItem("Open"); // open item for file menu
        MenuItem saveItem = new MenuItem("Save"); // save item for file menu
        MenuItem exitItem = new MenuItem("Exit"); // exit item for file menu
        openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN)); // shortcut Ctrl + O
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN)); // shortcut Ctrl + S
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN)); // shortcut Ctrl + X
        fileMenu.getItems().addAll(openItem, saveItem, exitItem); // add file menu items

        // edit menu
        Menu editMenu = new Menu("Edit"); // edit menu
        RadioMenuItem editRadioItem = new RadioMenuItem("Edit"); // edit radio item
        MenuItem addItem = new MenuItem("Add"); // add item
        MenuItem delItem = new MenuItem("Delete"); // delete item
        editMenu.getItems().addAll(editRadioItem, addItem, delItem); // add edit menu items

        // bar menu
        menuBar.getMenus().addAll(fileMenu, editMenu); // add file menu to bar menu
        root.setTop(menuBar); // set bar menu in top of layout

        // filename label
        Label label = new Label("Filename:"); // label for file name
        root.setBottom(label); // add label in bottom of layout

        // list view
        ListView<String> listView = new ListView<>(); // list view
        listView.setCellFactory(TextFieldListCell.forListView()); // set list view cell to text field
        root.setCenter(listView); // set list view in center

        openItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser(); // file chooser
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("List files", "*.lst"), // add extension *.lst
                    new FileChooser.ExtensionFilter("All files", "*.*") // add extension *.*
            ); // add extension to file chooser
            File file = fileChooser.showOpenDialog(primaryStage); // open file chooser and pick a file
            if (file != null) { // if file exist
                String file_name = file.getName(); // pick file name
                label.setText("Filename: " + file_name); // set file name in label
                String[] file_name_array = file_name.split("\\."); // divise file name by .
                if (file_name_array[file_name_array.length - 1].equals("lst")) { // check extension is *.lst
                    try { // try to read file
                        FileReader fileReader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        listView.getItems().clear(); // clear list view if it has an old items
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            listView.getItems().add(line); // add line to list view as item
                        }
                        bufferedReader.close();
                        fileReader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }); // open item handler

        saveItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser(); // file chooser
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("List files", "*.lst") // add extension *.lst
            ); // add extension to file chooser
            File file = fileChooser.showSaveDialog(primaryStage);
            try {
                FileWriter fileWriter = new FileWriter(file);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (int i = 0; i < listView.getItems().size(); i++) {
                    printWriter.println(listView.getItems().get(i).toString());
                }
                printWriter.close();
                fileWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }); // save item handler

        exitItem.setOnAction(e -> {
            System.exit(0);
        }); // exit item handler

        editRadioItem.setOnAction(e -> {
            listView.setEditable(editRadioItem.isSelected());
        }); // edit radio item handler

        addItem.setOnAction(e -> {
            if (listView.isEditable()) {
                listView.getItems().add("..."); // add item to list view
            }
        }); // add item handler

        delItem.setOnAction(e -> {
            if (listView.isEditable()) {
                int index = listView.getSelectionModel().getSelectedIndex();
                if (index != -1) {
                    listView.getItems().remove(index);
                }
            }
        }); // delete item handler

        Scene scene = new Scene(root, 300, 300); // add root layout to scene
        primaryStage.setScene(scene); // set scene on stage
        primaryStage.setTitle("Execrice 2"); // set title
        primaryStage.show(); // show stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
