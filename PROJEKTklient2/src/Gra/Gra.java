package Gra;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import MenuGlowne.Main;
import java.io.*;
import java.util.Random;

public class Gra {

    private String[][] stanGry = new String[3][3];
    private boolean ruchGracza = true;

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
    private Label ktoMaTerazRuch;


    private void dodajObrazek(Button przycisk, String sciezkaObrazka) {
        Image obrazek = new Image(getClass().getResource("/Tekstury/" + sciezkaObrazka).toExternalForm());
        ImageView imageView = new ImageView(obrazek);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        przycisk.setGraphic(imageView);
        przycisk.setDisable(true);
    }

    private void zapiszWynikiDoPliku(int wygranaGracz, int wygranaKomputer) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("statystyki.txt"));
            writer.write(Integer.toString(wygranaGracz));
            writer.newLine();
            writer.write(Integer.toString(wygranaKomputer));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void aktualizujStatystyki() {
        int wygranaGracz = 0;
        int wygranaKomputer = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("statystyki.txt"));
            wygranaGracz = Integer.parseInt(reader.readLine());
            wygranaKomputer = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (czyWygrana("O")) {
            wygranaGracz++;
        } else if (czyWygrana("X")) {
            wygranaKomputer++;
        }

        zapiszWynikiDoPliku(wygranaGracz, wygranaKomputer);
    }

    private void obsluzKlikniecie(Button przycisk, int wiersz, int kolumna) {
        if (ruchGracza && stanGry[wiersz][kolumna] == null) {
            stanGry[wiersz][kolumna] = "O";
            przycisk.setOpacity(1.0);
            dodajObrazek(przycisk, "kolko.png");
            przycisk.setDisable(true);

            if (czyWygrana("O"))
            {
                System.out.println("Gracz wygrał!");
                ktoMaTerazRuch.setText("Gracz1");
                aktualizujStatystyki();
            }
            else if (!czyPlanszaPelna())
            {
                ruchGracza = false;
                ktoMaTerazRuch.setText("Komputer");
                Service<Void> computerMoveService = new Service<>()
                {
                    @Override
                    protected Task<Void> createTask()
                    {
                        return new Task<>()
                        {
                            @Override
                            protected Void call()
                            {
                                try
                                {
                                    Thread.sleep(2000);
                                }
                                catch (InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        };
                    }
                };

                computerMoveService.setOnSucceeded(event -> {
                    Komputer();
                    if (czyWygrana("X")) {
                        System.out.println("Komputer wygrał!");
                        aktualizujStatystyki();
                    } else {
                        ktoMaTerazRuch.setText("Gracz1");
                        ruchGracza = true;
                    }
                });

                computerMoveService.start();
            } else {
                System.out.println("Remis!");
            }
        }
    }

    private void Komputer() {
        if (!czyPlanszaPelna()) {
            Random rand = new Random();
            int wiersz;
            int kolumna;
            do {
                wiersz = rand.nextInt(3);
                kolumna = rand.nextInt(3);
            } while (stanGry[wiersz][kolumna] != null);

            Button przycisk = getPrzycisk(wiersz, kolumna);
            stanGry[wiersz][kolumna] = "X";
            przycisk.setOpacity(1.0);
            dodajObrazek(przycisk, "krzyzyk.png");
            przycisk.setDisable(true);
            ruchGracza = true;
        }
    }

    private boolean czyPlanszaPelna() {
        for (String[] wiersz : stanGry) {
            for (String pole : wiersz) {
                if (pole == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private Button getPrzycisk(int wiersz, int kolumna) {
        switch (wiersz) {
            case 0:
                switch (kolumna) {
                    case 0: return pole1;
                    case 1: return pole2;
                    case 2: return pole3;
                }
                break;
            case 1:
                switch (kolumna) {
                    case 0: return pole4;
                    case 1: return pole5;
                    case 2: return pole6;
                }
                break;
            case 2:
                switch (kolumna) {
                    case 0: return pole7;
                    case 1: return pole8;
                    case 2: return pole9;
                }
                break;
        }
        return null;
    }

    private boolean czyWygrana(String gracz) {
        for (int i = 0; i < 3; i++) {
            if (stanGry[i][0] == gracz && stanGry[i][1] == gracz && stanGry[i][2] == gracz) {
                zablokujWszystkiePrzyciski();
                return true;
            }
            if (stanGry[0][i] == gracz && stanGry[1][i] == gracz && stanGry[2][i] == gracz) {
                zablokujWszystkiePrzyciski();
                return true;
            }
        }
        if ((stanGry[0][0] == gracz && stanGry[1][1] == gracz && stanGry[2][2] == gracz) ||
                (stanGry[0][2] == gracz && stanGry[1][1] == gracz && stanGry[2][0] == gracz)) {
            zablokujWszystkiePrzyciski();
            return true;
        }
        return false;
    }


    private void zablokujWszystkiePrzyciski() {
        pole1.setDisable(true);
        pole2.setDisable(true);
        pole3.setDisable(true);
        pole4.setDisable(true);
        pole5.setDisable(true);
        pole6.setDisable(true);
        pole7.setDisable(true);
        pole8.setDisable(true);
        pole9.setDisable(true);
    }


    @FXML
    void pole1(ActionEvent event) {
        obsluzKlikniecie(pole1, 0, 0);
    }

    @FXML
    void pole2(ActionEvent event) {
        obsluzKlikniecie(pole2, 0, 1);
    }

    @FXML
    void pole3(ActionEvent event) {
        obsluzKlikniecie(pole3, 0, 2);
    }

    @FXML
    void pole4(ActionEvent event) {
        obsluzKlikniecie(pole4, 1, 0);
    }

    @FXML
    void pole5(ActionEvent event) {
        obsluzKlikniecie(pole5, 1, 1);
    }

    @FXML
    void pole6(ActionEvent event) {
        obsluzKlikniecie(pole6, 1, 2);
    }

    @FXML
    void pole7(ActionEvent event) {
        obsluzKlikniecie(pole7, 2, 0);
    }

    @FXML
    void pole8(ActionEvent event) {
        obsluzKlikniecie(pole8, 2, 1);
    }

    @FXML
    void pole9(ActionEvent event) {
        obsluzKlikniecie(pole9, 2, 2);
    }

    @FXML
    void przyciskPowrot(ActionEvent event) throws IOException {
        Main.changeScene("/FXML/MenuGlowne.fxml");
    }

}
