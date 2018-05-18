package tp6.images_version;

import javafx.scene.control.Button;

public class Carte {
    public int id;
    public String url;
    public Button button;
    public boolean open;

    Carte(int id, String url) {
        this.id = id;
        this.url = url;
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
        button.setStyle("-fx-background-image: url('" + getClass().getResource(url).toExternalForm() + "');");
        open = true;
    }

    public void closeButton() {
        button.setStyle("-fx-background-image: url('" + getClass().getResource("images/Blank.png").toExternalForm() + "')");
        open = false;
    }

    public static boolean checkCartes(Carte carte1, Carte carte2) {
        return (carte1.url == carte2.url);
    }
}
