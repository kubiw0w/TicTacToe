package Gra;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Kontroler obslugujacy klienta w grze wieloosobowej.
 */
public class Klient {

    @FXML
    private Label tekstTytul;

    @FXML
    private Label tekstPokoje;

    @FXML
    private Label tekstPolaczono;

    @FXML
    private Button przyciskPolacz;

    @FXML
    private Button pole1;

    @FXML
    private Button pole2;

    @FXML
    private Button pole3;

    @FXML
    private Button pole4;

    @FXML
    private Button pole5;

    @FXML
    private Button pole6;

    @FXML
    private Button pole7;

    @FXML
    private Button pole8;

    @FXML
    private Button pole9;

    @FXML
    private Label tekstKlient1;

    @FXML
    private Label tekstKlient2;

    @FXML
    private ImageView zdjecieKolko;

    @FXML
    private ImageView zdjecieKrzyzyk;

    @FXML
    private Label tekstNick;

    private static final String ADRES_SERWERA = "127.0.0.1";
    private static final int PORT = 5555;

    private String[][] stanGry = new String[3][3];

    /**
     * Obsluguje zdarzenie nacisniecia przycisku "Polacz", inicjujac polaczenie z serwerem.
     *
     * @param event Zdarzenie nacisniecia przycisku.
     */
    @FXML
    void przyciskPolacz(ActionEvent event) {
        przyciskPolacz.setVisible(false);
        try {
            Socket socket = new Socket(ADRES_SERWERA, PORT);
            tekstPolaczono.setText("Dołączono do pokoju");

            new Thread(() -> {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    // Odbierz komunikat od serwera
                    String receivedMessage = (String) inputStream.readObject();

                    // Jeśli otrzymałeś odpowiedni komunikat, uruchom licznik czasu
                    if ("Obaj gracze się połączyli!".equals(receivedMessage)) {
                        Platform.runLater(() -> komponenty());
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
            tekstPolaczono.setText("Błąd połączenia z serwerem");
        }
    }

    /**
     * Inicjalizuje komponenty gry po nawiazaniu polaczenia z serwerem.
     */
    private void komponenty() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(3),
                ae -> {
                    tekstTytul.setVisible(false);
                    tekstPokoje.setVisible(false);
                    tekstPolaczono.setVisible(false);
                    przyciskPolacz.setVisible(false);
                    pole1.setVisible(true);
                    pole2.setVisible(true);
                    pole3.setVisible(true);
                    pole4.setVisible(true);
                    pole5.setVisible(true);
                    pole6.setVisible(true);
                    pole7.setVisible(true);
                    pole8.setVisible(true);
                    pole9.setVisible(true);
                    tekstKlient1.setVisible(true);
                    tekstKlient2.setVisible(true);
                    zdjecieKolko.setVisible(true);
                    zdjecieKrzyzyk.setVisible(true);
                    tekstNick.setVisible(true);
                }));
        timeline.play();
    }

    /**
     * Dodaje obrazek do przycisku i aktualizuje stan gry.
     *
     * @param przycisk Przycisk, do ktorego dodawany jest obraz.
     */
    private void dodajObrazek(Button przycisk, String sciezkaObrazka) {
        Image obrazek = new Image(getClass().getResource("/Tekstury/" + sciezkaObrazka).toExternalForm());
        ImageView imageView = new ImageView(obrazek);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        przycisk.setGraphic(imageView);
    }



    @FXML
    void pole1(ActionEvent event) {
        dodajObrazek(pole1, "kolkoasd.png");
        stanGry[0][0] = "O";
    }

    @FXML
    void pole2(ActionEvent event) {

    }

    @FXML
    void pole3(ActionEvent event) {

    }

    @FXML
    void pole4(ActionEvent event) {

    }

    @FXML
    void pole5(ActionEvent event) {

    }

    @FXML
    void pole6(ActionEvent event) {

    }

    @FXML
    void pole7(ActionEvent event) {

    }

    @FXML
    void pole8(ActionEvent event) {

    }

    @FXML
    void pole9(ActionEvent event) {

    }

    @FXML
    void przyciskPowrot(ActionEvent event) {

    }
}
