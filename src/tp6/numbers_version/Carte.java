package tp6.numbers_version;

import javafx.scene.control.Button;

public class Carte {
    public int id;
    public int value;
    public Button button;
    public boolean open;

    Carte(int id, int value) {
        this.id = id;
        this.value = value;
        open = false;
        button = new Button("");
    }

    public Button getButton() {
        return button;
    }

    public void disableButton() {
        button.setDisable(true);
    }

    public void openButton() {
        button.setText(value + "");
        open = true;
    }

    public void closeButton() {
        button.setText("");
        open = false;
    }

    public static boolean checkCartes(Carte carte1, Carte carte2) {
        return (carte1.value == carte2.value);
    }
}
