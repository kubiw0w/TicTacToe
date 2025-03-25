package Gra;


import MenuGlowne.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

/**
 * Kontroler obslugujacy widok wyboru przeciwnika w grze w kolko i krzyzyk.
 */
public class WyborPrzeciwnika {

    @FXML
    private RadioButton wyborKomputer;

    @FXML
    private RadioButton wyborPrzeciwnik;

    private ToggleGroup toggleGroup;

    /**
     * Inicjalizuje kontroler po zaladowaniu widoku.
     */
    @FXML
    void initialize() {
        toggleGroup = new ToggleGroup();
        wyborKomputer.setToggleGroup(toggleGroup);
        wyborPrzeciwnik.setToggleGroup(toggleGroup);
        wyborKomputer.setSelected(true);
    }

    /**
     * Obsluguje zdarzenie przycisku potwierdzenia.
     *
     * @param event Zdarzenie przycisku.
     * @throws IOException Wystepuje, gdy wystapi problem z ladowaniem nowego widoku.
     */
    @FXML
    void przyciskPotwierdz(ActionEvent event) throws IOException {
        if (toggleGroup.getSelectedToggle() != null) {
            if (toggleGroup.getSelectedToggle() == wyborKomputer) {
                Main.changeScene("/FXML/Gra.fxml");
            } else if (toggleGroup.getSelectedToggle() == wyborPrzeciwnik) {
                Main.changeScene("/FXML/Serwer.fxml");
            }
        }
    }

    /**
     * Obsluguje zdarzenie przycisku powrotu.
     *
     * @param event Zdarzenie przycisku.
     * @throws IOException Wystepuje, gdy wystapi problem z ladowaniem nowego widoku.
     */
    @FXML
    void przyciskPowrot(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/MenuGlowne.fxml");
    }

    /**
     * Obsluguje zdarzenie wyboru opcji gry z komputerem.
     *
     * @param event Zdarzenie wyboru opcji.
     */
    @FXML
    void wyborKomputer(ActionEvent event) {

    }

    /**
     * Obsluguje zdarzenie wyboru opcji gry z drugim graczem.
     *
     * @param event Zdarzenie wyboru opcji.
     */
    @FXML
    void wyborPrzeciwnik(ActionEvent event) {

    }

}
