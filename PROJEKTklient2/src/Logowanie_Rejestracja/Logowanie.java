package Logowanie_Rejestracja;


import MenuGlowne.MenuGlowne;
import MenuGlowne.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Logowanie {

    @FXML
    private TextField poleLogin;

    @FXML
    private PasswordField poleHaslo;

    @FXML
    private Label czyZalogowano;

    private static String komunikat = "";

    public static void setKomunikat(String message)
    {
        komunikat = message;
    }

    @FXML
    public void initialize() {
        czyZalogowano.setText(komunikat);
        komunikat = "";
    }

    @FXML
    void przyciskPowrot(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/MenuGlowne.fxml");
    }

    private boolean czyPoprawneLogowanie(String login, String haslo) throws IOException {
        File file = new File("dane.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dane = linia.split(":");
                if (dane.length > 1 && dane[0].equals(login) && dane[1].equals(haslo)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        }
        return false;
    }

    @FXML
    void przyciskZaloguj(ActionEvent event) throws IOException {
        String login = poleLogin.getText();
        String haslo = poleHaslo.getText();

        if (czyPoprawneLogowanie(login, haslo)) {
            MenuGlowne.setKomunikat("Witamy!");
            Main.changeScene("/FXML/MenuGlowne.fxml");
        } else {
            czyZalogowano.setText("Błędne dane logowania.");
        }
    }

    @FXML
    void przyciskRejestracja(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/Rejestracja.fxml");
    }

}
