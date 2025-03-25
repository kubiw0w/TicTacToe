package Gra;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentujaca serwer dla gry w kółko i krzyżyk obsługujacy komunikacje z klientami.
 */
public class Serwer {
    private static final int PORT = 5555;
    private static final int MAX_CLIENTS = 2;
    private ServerSocket serverSocket;
    private int polaczeniKlienci = 0;
    private List<ObjectOutputStream> outputStreams = new ArrayList<>();
    private String[][] stanGry = new String[3][3];

    @FXML
    private Button przyciskStworz;

    @FXML
    private Label tekst1;

    @FXML
    private Label tekst2;

    /**
     * Obsluguje zdarzenie przycisku powrotu.
     *
     * @param event Zdarzenie przycisku.
     */
    @FXML
    void przyciskPowrot(ActionEvent event) {

    }

    /**
     * Obsluguje zdarzenie przycisku tworzenia serwera.
     *
     * @param event Zdarzenie przycisku.
     */
    @FXML
    void przyciskStworz(ActionEvent event) {
        przyciskStworz.setVisible(false);
        if (polaczeniKlienci < MAX_CLIENTS) {
            new Thread(() -> {
                try {
                    serverSocket = new ServerSocket(PORT);
                    Platform.runLater(() -> tekst1.setText("Graczy: " + polaczeniKlienci + "/2. Oczekiwanie na graczy..."));

                    while (polaczeniKlienci < MAX_CLIENTS) {
                        Socket clientSocket = serverSocket.accept();
                        polaczeniKlienci++;

                        final int aktualnaLiczbaGraczy = polaczeniKlienci;

                        Platform.runLater(() -> {
                            tekst1.setText("Graczy: " + aktualnaLiczbaGraczy + "/2");
                            if (aktualnaLiczbaGraczy == MAX_CLIENTS) {
                                tekst1.setText("Graczy: " + aktualnaLiczbaGraczy + "/2. Gra rozpoczęta!");

                                // Wysyłamy informację do obu klientów
                                for (ObjectOutputStream outputStream : outputStreams) {
                                    try {
                                        outputStream.writeObject("Obaj gracze się połączyli!");
                                        outputStream.flush();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });

                        // Pobieramy strumień wyjściowy klienta i dodajemy do listy
                        ObjectOutputStream clientOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                        outputStreams.add(clientOutputStream);

                        // Obsługa klienta, np. utworzenie nowego wątku do komunikacji z klientem
                        new Thread(() -> {
                            // Tu możesz umieścić kod obsługujący komunikację z klientem
                            // np. wysyłanie i odbieranie danych
                        }).start();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } else {
            Platform.runLater(() -> tekst1.setText("Osiągnięto maksymalną liczbę klientów"));
        }
    }

    /**
     * Sprawdza, czy dany gracz wygral w grze.
     *
     * @param gracz Oznaczenie gracza ("O" lub "X").
     * @return True, jesli gracz wygral; w przeciwnym razie false.
     */
    private boolean czyWygrana(String gracz) {
        for (int i = 0; i < 3; i++) {
            if (stanGry[i][0] == gracz && stanGry[i][1] == gracz && stanGry[i][2] == gracz) {
                return true;
            }
            if (stanGry[0][i] == gracz && stanGry[1][i] == gracz && stanGry[2][i] == gracz) {
                return true;
            }
        }
        if ((stanGry[0][0] == gracz && stanGry[1][1] == gracz && stanGry[2][2] == gracz) ||
                (stanGry[0][2] == gracz && stanGry[1][1] == gracz && stanGry[2][0] == gracz)) {
            return true;
        }
        return false;
    }
}
