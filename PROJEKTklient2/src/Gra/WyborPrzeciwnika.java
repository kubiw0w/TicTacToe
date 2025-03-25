package Gra;


import MenuGlowne.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class WyborPrzeciwnika {

    @FXML
    private RadioButton wyborKomputer;

    @FXML
    private RadioButton wyborPrzeciwnik;

    private ToggleGroup toggleGroup;

    @FXML
    void initialize() {
        toggleGroup = new ToggleGroup();
        wyborKomputer.setToggleGroup(toggleGroup);
        wyborPrzeciwnik.setToggleGroup(toggleGroup);
        wyborKomputer.setSelected(true);
    }

    @FXML
    void przyciskPotwierdz(ActionEvent event) throws IOException {
        if (toggleGroup.getSelectedToggle() != null) {
            if (toggleGroup.getSelectedToggle() == wyborKomputer) {
                Main.changeScene("/FXML/Gra.fxml");
            } else if (toggleGroup.getSelectedToggle() == wyborPrzeciwnik) {
                Main.changeScene("/FXML/Klient.fxml");
            }
        }
    }

    @FXML
    void przyciskPowrot(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/MenuGlowne.fxml");
    }

    @FXML
    void wyborKomputer(ActionEvent event) {

    }

    @FXML
    void wyborPrzeciwnik(ActionEvent event) {

    }

}
