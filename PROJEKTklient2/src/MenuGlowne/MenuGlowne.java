package MenuGlowne;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuGlowne {

    @FXML
    private Label tekstLogowanie;

    private static String komunikat = "";

    public static void setKomunikat(String message){
        komunikat = message;
    }

    @FXML
    public void initialize() {
        tekstLogowanie.setText(komunikat);
        komunikat = "";
    }

    @FXML
    void przyciskGraj(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/WyborPrzeciwnika.fxml");
    }

    @FXML
    void przyciskStatystyki(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/Statystyki.fxml");
    }

    @FXML
    void przyciskZaloguj(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/Logowanie.fxml");
    }

    @FXML
    void przyciskWyjdz(ActionEvent event) {
        System.exit(0);
    }
}