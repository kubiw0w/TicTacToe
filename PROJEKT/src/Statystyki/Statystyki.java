package Statystyki;

import MenuGlowne.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Kontroler obslugujący widok statystyk gry.
 */
public class Statystyki {

    @FXML
    private Label liczbaWygranychGracza;

    @FXML
    private Label liczbaWygranychKomputera;

    /**
     * Inicjalizuje kontroler, ustawiajac liczby wygranych gracza i komputera z danych z pliku.
     */
    @FXML
    void initialize() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("statystyki.txt"));
            String linia;
            int numerLinii = 1;
            while ((linia = br.readLine()) != null) {
                if (numerLinii == 1) {
                    liczbaWygranychGracza.setText(linia);
                } else if (numerLinii == 2) {
                    liczbaWygranychKomputera.setText(linia);
                }
                numerLinii++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obsluguje zdarzenie nacisniecia przycisku "Powrot", przechodzac do glownego menu.
     *
     * @param event Zdarzenie nacisniecia przycisku.
     * @throws IOException Wystepuje, gdy wystapi problem podczas ladowania widoku.
     */
    @FXML
    void przyciskPowrot(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/MenuGlowne.fxml");
    }
}
