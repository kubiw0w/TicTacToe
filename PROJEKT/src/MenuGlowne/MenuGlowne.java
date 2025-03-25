package MenuGlowne;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Kontroler obslugujący interakcje uzytkownika w glownym menu.
 */
public class MenuGlowne {

    @FXML
    private Label tekstLogowanie;

    private static String komunikat = "";

    /**
     * Ustawia komunikat do wyswietlenia w etykiecie tekstowej.
     *
     * @param message Komunikat do wyswietlenia.
     */
    public static void setKomunikat(String message){
        komunikat = message;
    }

    /**
     * Inicjalizuje kontroler, ustawiajac tekst komunikatu.
     */
    @FXML
    public void initialize() {
        tekstLogowanie.setText(komunikat);
        komunikat = "";
    }

    /**
     * Obsluguje zdarzenie nacisniecia przycisku "Graj", przechodzac do widoku wyboru przeciwnika.
     *
     * @param event Zdarzenie nacisniecia przycisku.
     * @throws IOException Wystepuje, gdy wystapi problem podczas ladowania widoku.
     */
    @FXML
    void przyciskGraj(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/WyborPrzeciwnika.fxml");
    }

    /**
     * Obsluguje zdarzenie nacisniecia przycisku "Statystyki", przechodzac do widoku statystyk.
     *
     * @param event Zdarzenie nacisniecia przycisku.
     * @throws IOException Wystepuje, gdy wystapi problem podczas ladowania widoku.
     */
    @FXML
    void przyciskStatystyki(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/Statystyki.fxml");
    }

    /**
     * Obsluguje zdarzenie nacisniecia przycisku "Zaloguj", przechodzac do widoku logowania.
     *
     * @param event Zdarzenie nacisniecia przycisku.
     * @throws IOException Wystepuje, gdy wystapi problem podczas ladowania widoku.
     */
    @FXML
    void przyciskZaloguj(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/Logowanie.fxml");
    }

    /**
     * Obsluguje zdarzenie nacisniecia przycisku "Wyjdz", zamykajac aplikacje.
     *
     * @param event Zdarzenie nacisniecia przycisku.
     */
    @FXML
    void przyciskWyjdz(ActionEvent event) {
        System.exit(0);
    }
}