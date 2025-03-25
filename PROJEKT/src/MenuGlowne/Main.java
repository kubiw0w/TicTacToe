package MenuGlowne;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Glowna klasa aplikacji, inicjalizujaca i uruchamiajaca interfejs uzytkownika.
 */
public class Main extends Application {

    private static Stage primaryStage;

    /**
     * Metoda startujaca aplikacje, wczytuje glowny widok menu.
     *
     * @param primaryStage Glowny stage (okno) aplikacji.
     * @throws Exception Wystepuje, gdy wystapi problem podczas ladowania widoku.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("../FXML/MenuGlowne.fxml"));
        primaryStage.setTitle("Kółko i krzyżyk");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Metoda uruchamiajaca aplikacje.
     *
     * @param args Argumenty przekazywane do aplikacji.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metoda zmieniajaca aktualny widok na podany plik FXML.
     *
     * @param fxml Sciezka do pliku FXML reprezentujacego nowy widok.
     * @throws IOException Wystepuje, gdy wystapi problem podczas ladowania nowego widoku.
     */
    public static void changeScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(fxml));
        primaryStage.setScene(new Scene(root));
    }
}