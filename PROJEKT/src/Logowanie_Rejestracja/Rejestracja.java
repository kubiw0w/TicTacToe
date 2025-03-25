package Logowanie_Rejestracja;


import MenuGlowne.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;

/**
 * Kontroler obslugujacy proces rejestracji nowego uzytkownika.
 */
public class Rejestracja {

    @FXML
    private TextField poleLogin;

    @FXML
    private PasswordField poleHaslo;

    @FXML
    private Label czyDanePoprawne;

    /**
     * Obsluguje zdarzenie nacisniecia przycisku powrotu do ekranu logowania.
     *
     * @param event Zdarzenie przycisku.
     * @throws IOException Wystepuje, gdy wystapi problem z ladowaniem nowego widoku.
     */
    @FXML
    void przyciskPowrot(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/Logowanie.fxml");
    }

    /**
     * Sprawdza, czy uzytkownik o podanym loginie juz istnieje.
     *
     * @param login Login uzytkownika.
     * @return True, jesli uzytkownik o podanym loginie istnieje, false w przeciwnym razie.
     * @throws IOException Wystepuje, gdy wystapi problem z odczytem danych z pliku.
     */
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

    /**
     * Zapisuje dane nowego uzytkownika do pliku.
     *
     * @param login Login uzytkownika.
     * @param haslo Haslo uzytkownika.
     * @throws IOException Wystepuje, gdy wystapi problem z zapisem danych do pliku.
     */
    private void zapiszDane(String login, String haslo) throws IOException {
        if (!uzytkownikIstnieje(login)) {
            FileWriter writer = new FileWriter("dane.txt", true);
            writer.write(login + ":" + haslo + "\n");
            writer.close();
        } else {
            czyDanePoprawne.setText("Użytkownik o podanym loginie już istnieje!");
        }
    }

    /**
     * Obsluguje zdarzenie nacisniecia przycisku zarejestruj.
     *
     * @param event Zdarzenie przycisku.
     * @throws IOException Wystepuje, gdy wystapi problem z ladowaniem nowego widoku.
     */
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

