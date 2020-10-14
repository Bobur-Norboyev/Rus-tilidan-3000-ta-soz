package Controller;

import Baza.BazagaUlanish;
import BoshqaKlass.Vaqtincha;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class IzlashController implements Initializable {

    @FXML
    private JFXRadioButton uzb, rus;
    @FXML
    private JFXListView<Label> ruyhat;
    @FXML
    private JFXTextField suz;
    @FXML
    private JFXHamburger hamb;
    @FXML
    private AnchorPane asosiyPanel;

    protected String ustun;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HamburgerBackArrowBasicTransition ortga = new HamburgerBackArrowBasicTransition(hamb);
        ortga.setRate(1);
        ortga.play();

        hamb.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                chiqishHodisasi(event);
            }
        });

        asosiyPanel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.B) {
                    chiqishHodisasi(event);
                }
            }
        });

        ToggleGroup guruh = new ToggleGroup();
        guruh.getToggles().addAll(uzb, rus);
        uzb.setSelected(true);
        ustun = "soz_uz";
        guruh.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue.equals(uzb)) {
                    ustun = "soz_uz";
                }
                if (newValue.equals(rus)) {
                    ustun = "soz_ru";
                }
            }
        });

        suz.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    BazagaUlanish baza = new BazagaUlanish();
                    List<String> ruyhat1 = new ArrayList<>();
                    List<Boolean> selected;
                    int s;
                    if (uzb.isSelected()) {
                        s = 1;
                    } else {
                        s = 2;
                    }
                    String satr = "SELECT * FROM sozlar WHERE " + ustun + " LIKE '%" + formatlash(newValue) + "%'";
                    ruyhat1 = baza.tanlabOl(satr, s);
                    selected = baza.select();
                    ruyhat.getItems().clear();
                    for (int i = 0; i < ruyhat1.size(); i++) {
                        Label l1 = new Label("    " + ruyhat1.get(i));
                        l1.setFont(new Font("Times new Roman", 20));
                        ruyhat.getItems().add(l1);
                    }
                }
            }
        });

        ruyhat.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (ruyhat.getSelectionModel().getSelectedIndex() >= 0) {
                    int k = 1;
                    if (uzb.isSelected()) {
                        k = 1;
                    }
                    if (rus.isSelected()) {
                        k = 2;
                    }
                    tarjimaKorsat(ruyhat.getSelectionModel().getSelectedItem().getText().toString(), k);
                }
            }
        });
        ruyhat.setOnMousePressed(e -> {
            if (ruyhat.getSelectionModel().getSelectedIndex() >= 0) {
                int k = 1;
                if (uzb.isSelected()) {
                    k = 1;
                }
                if (rus.isSelected()) {
                    k = 2;
                }
                tarjimaKorsat(ruyhat.getSelectionModel().getSelectedItem().getText().toString(), k);
            }
        });
    }

    private String formatlash(String satr) {
        char[] massiv = new char[satr.length()];
        massiv = satr.toCharArray();
        satr = "";
        for (char x : massiv) {
            if (x == '\'') {
                satr += String.format("%c", '_');
            } else {
                satr += String.format("%c", x);
            }
        }
        return satr;
    }

    public void chiqishHodisasi(InputEvent oby) {
        Node source = (Node) oby.getSource();
        Stage joriy = (Stage) source.getScene().getWindow();
        joriy.close();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/AsosiySahna.fxml"));
        } catch (IOException ex) {
            System.out.println("Asosiy Sahnani yuklab bo'lmadi");
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Rus tilidan 3000 ta so'z");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/Image/logo.png"));
        stage.show();
    }

    private void tarjimaKorsat(String soz, int tili) {
        soz = soz.substring(4);
        String sorov = null;
        if (tili == 1) {
            sorov = "SELECT soz_uz, soz_ru, turkum_id,tanlangan, audio, id  FROM sozlar WHERE soz_uz LIKE '" + formatlash(soz) + "'";
        }
        if (tili == 2) {
            sorov = "SELECT soz_uz, soz_ru, turkum_id,tanlangan, audio, id FROM sozlar WHERE soz_ru='" + soz + "'";
        }
        BazagaUlanish ulan = new BazagaUlanish();
        try {
            ulan.tarjimaQil(sorov, tili);
        } catch (ClassNotFoundException ex) {
            System.out.println("Baza driveri topilmadi");
        } catch (SQLException ex) {
            System.out.println("Baza bilan bog'lanishdagi xatolik");
        }
        Vaqtincha.javoblarniOzgartir(ulan.uzi, ulan.tarj, ulan.audio, ulan.turkum, ulan.sevimli, ulan.id);
        NatijaKorsat();

    }

    private void NatijaKorsat() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/Natija.fxml"));
        } catch (IOException ex) {
            System.out.println("yuklanmadi");
        }
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("/Image/logo.png"));
        stage.show();
    }

}
