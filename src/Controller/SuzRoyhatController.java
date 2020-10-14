package Controller;

import Baza.BazagaUlanish;
import BoshqaKlass.Vaqtincha;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.*;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SuzRoyhatController implements Initializable {

    @FXML
    private JFXHamburger drawer_ochish;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    Label sarlavha;
    @FXML
    AnchorPane asosiyAnchor;
    @FXML
    JFXListView<Label> list;
    @FXML
    private JFXRadioButton uzbek, rus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        list.getStylesheets().add("/Css/listview_stil.css");

        ToggleGroup guruh = new ToggleGroup();
        guruh.getToggles().add(uzbek);
        guruh.getToggles().add(rus);
        guruh.selectToggle(uzbek);

        guruh.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == rus) {
                    qaytaYuklash(2);
                }
                if (newValue == uzbek) {
                    qaytaYuklash(1);
                }
            }
        });

        //ortga qaytish tugmasiga qiymatlar o'rnatilyapti
        HamburgerBackArrowBasicTransition ortga_qayt = new HamburgerBackArrowBasicTransition(drawer_ochish);
        ortga_qayt.setRate(1);
        ortga_qayt.play();

        sarlavha.setText(Vaqtincha.bolimNomi);

        drawer_ochish.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                chiqishHodisasi(event);
            }
        });

        asosiyAnchor.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.B) {
                    chiqishHodisasi(event);
                }
                if (event.getCode() == KeyCode.LEFT) {
                    uzbek.setSelected(true);
                    rus.setSelected(false);
                    list.getSelectionModel().select(0);
                }
                if (event.getCode() == KeyCode.RIGHT) {
                    uzbek.setSelected(false);
                    rus.setSelected(true);
                    list.getSelectionModel().select(0);
                }
            }
        });

        list.setExpanded(true);
        list.depthProperty().set(1);

        
        //so'zlarni o'qib olib, listga joylash uchun oqim ishga tushirildi
        Thread oqim = new Thread(new Runnable() {
            @Override
            public void run() {
         BazagaUlanish baza = new BazagaUlanish();
        List<String> ruyhat1 = new ArrayList<>();
        List<Boolean> selected;
        try {
            ruyhat1 = baza.ulanish("SELECT sozlar.id, sozlar.soz_uz, sozlar.tanlangan FROM sozlar WHERE turkum_id=");
            selected = baza.select();
            for (int i = 0; i < ruyhat1.size(); i++) {
                Label l1 = new Label("    " + ruyhat1.get(i));
                if (!selected.get(i)) {
                    l1.setGraphic(new ImageView(new Image("/Image/tanlanmagan.png")));
                }
                if (selected.get(i)) {
                    l1.setGraphic(new ImageView(new Image("/Image/tanlangan.png")));
                }
                l1.setFont(new Font("Times new Roman", 20));
                list.getItems().add(l1);
            }
        } catch (Exception ex) {
            System.out.println("ulanish amalga oshmadi  :( :(");
        }
            }
        });

        oqim.start();
        
        list.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (list.getSelectionModel().getSelectedIndex() >= 0) {
                    int k = 1;
                    if (uzbek.isSelected()) {
                        k = 1;
                    }
                    if (rus.isSelected()) {
                        k = 2;
                    }
                    tarjimaKorsat(list.getSelectionModel().getSelectedItem().getText().toString(), k);
                }
            }
        });
        list.setOnMousePressed(e -> {
            if (list.getSelectionModel().getSelectedIndex() >= 0) {
                int k = 1;
                if (uzbek.isSelected()) {
                    k = 1;
                }
                if (rus.isSelected()) {
                    k = 2;
                }
                tarjimaKorsat(list.getSelectionModel().getSelectedItem().getText().toString(), k);
            }
        });

    }

    private void qaytaYuklash(int a) {
        BazagaUlanish baza = new BazagaUlanish();
        List<String> ruyhat1 = new ArrayList<>();
        List<Boolean> selected;
        try {
            String satr = null;
            if (a == 1) {
                satr = "SELECT  sozlar.id, sozlar.soz_uz, sozlar.tanlangan FROM sozlar WHERE turkum_id=";
            }
            if (a == 2) {
                satr = "SELECT  sozlar.id, sozlar.soz_ru, sozlar.tanlangan FROM sozlar WHERE turkum_id=";
            }
            ruyhat1 = baza.ulanish(satr);
            selected = baza.select();
            System.out.println("Bazadan elementlar olib olindi. Endi faqat ListView ga qo'yib chiqish kerak xolos.");
            list.getItems().clear();
            for (int i = 0; i < ruyhat1.size(); i++) {
                Label l1 = new Label("    " + ruyhat1.get(i));
                if (!selected.get(i)) {
                    l1.setGraphic(new ImageView(new Image("/Image/tanlanmagan.png")));
                }
                if (selected.get(i)) {
                    l1.setGraphic(new ImageView(new Image("/Image/tanlangan.png")));
                }
                l1.setFont(new Font("Times new Roman", 20));
                list.getItems().add(l1);
            }
        } catch (Exception ex) {
            System.out.println("ulanish amalga oshmadi  :( :(");
        }
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

}
