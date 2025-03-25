package Logowanie_Rejestracja;


import MenuGlowne.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;

public class Rejestracja {

    @FXML
    private TextField poleLogin;

    @FXML
    private PasswordField poleHaslo;

    @FXML
    private Label czyDanePoprawne;

    @FXML
    void przyciskPowrot(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/Logowanie.fxml");
    }

    private boolean uzytkownikIstnieje(String login) throws IOException {
        File file = new File("dane.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dane = linia.split(":");
                if (dane.length > 0 && dane[0].equals(login)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        }
        return false;
    }

    private void zapiszDane(String login, String haslo) throws IOException {
        if (!uzytkownikIstnieje(login)) {
            FileWriter writer = new FileWriter("dane.txt", true);
            writer.write(login + ":" + haslo + "\n");
            writer.close();
        } else {
            czyDanePoprawne.setText("Użytkownik o podanym loginie już istnieje!");
        }
    }

    @FXML
    void przyciskZarejestruj(ActionEvent event) throws IOException {
        String login = poleLogin.getText();
        String haslo = poleHaslo.getText();

        if (uzytkownikIstnieje(login)) {
            czyDanePoprawne.setText("Użytkownik o podanym loginie już istnieje!");
        } else {
            zapiszDane(login, haslo);
            Logowanie.setKomunikat("Rejestracja przebiegła pomyślnie. Teraz możesz się zalogować.");
            Main.changeScene("/FXML/Logowanie.fxml");
        }
    }

}

