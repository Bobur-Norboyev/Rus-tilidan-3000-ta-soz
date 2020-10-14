package Controller;

import Baza.BazagaUlanish;
import BoshqaKlass.Vaqtincha;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class NatijaController implements Initializable {

    @FXML
    private JFXTextField tarjima, turkum;
    @FXML
    private JFXButton ortga, tinglash;
    @FXML
    private Label soz;
    @FXML
    private JFXHamburger back;
    @FXML
    private AnchorPane anchor;
    @FXML
    private FontAwesomeIconView rasm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        JFXSnackbar snekbar = new JFXSnackbar ( anchor);
        String stil = NatijaController.class.getClass().getResource("/Css/snackbar.css").toExternalForm();
        snekbar.getStylesheets().add(stil);
        
        soz.setText(Vaqtincha.ozi);
        tarjima.setText(Vaqtincha.tarjima);
        switch (Vaqtincha.turkumId) {
            case 1:
                turkum.setText("Ot");
                break;
            case 2:
                turkum.setText("Sifat");
                break;
            case 3:
                turkum.setText("Fe'l");
                break;
            default:
                turkum.setText("Noma'lum");
        }

        Integer sel = Vaqtincha.tanlangan;
        if (sel == 1) {
            rasm.setIcon(FontAwesomeIcon.STAR);
            rasm.setUserData(Boolean.TRUE);
        }
        if (sel == 0) {
            rasm.setIcon(FontAwesomeIcon.STAR_ALT);
            rasm.setUserData(Boolean.FALSE);
        }

        HamburgerBackArrowBasicTransition trans = new HamburgerBackArrowBasicTransition(back);
        trans.setRate(1);
        trans.play();

        back.setOnMousePressed((MouseEvent event) -> {
        });

        rasm.setOnMousePressed((MouseEvent event) -> {
            BazagaUlanish con = new BazagaUlanish();
            
            if (rasm.getUserData().equals(Boolean.FALSE)) {
                rasm.setIcon(FontAwesomeIcon.STAR);
                rasm.setUserData(Boolean.TRUE);
                con.selectionMethod(Vaqtincha.id, 1);
                snekbar.show("\"" + Vaqtincha.ozi + "\" tanlanganlarga qo'shildi !", 3000);
            } else {
                rasm.setIcon(FontAwesomeIcon.STAR_ALT);
                rasm.setUserData(Boolean.FALSE);
                con.selectionMethod(Vaqtincha.id, 0);
                snekbar.show("\"" + Vaqtincha.ozi + "\" tanlanganlardan o'chirildi !", 3000);
            }
        });

        anchor.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.B) {
                Node source = (Node) event.getSource();
                Stage joriy = (Stage) source.getScene().getWindow();
                joriy.close();
            }
        });

        back.setOnMousePressed((MouseEvent event) -> {
            Node source = (Node) event.getSource();
            Stage joriy = (Stage) source.getScene().getWindow();
            joriy.close();
        });

        ortga.setOnAction((ActionEvent event) -> {
            Node source = (Node) event.getSource();
            Stage joriy = (Stage) source.getScene().getWindow();
            joriy.close();
        });

        tinglash.setOnMouseClicked((MouseEvent event) -> {
            /*
            
            !!!!!!!!!!         Muallifga eslatma      !!!!!!!!!!!!!!
            
            Bu yerga audio faylni yuklash kodlari yoziladi.
            So'zga biriktirilgan audio faylning nomini "Vaqtincha.audio_nomi" dan olinadi
            Bu nomli audio fayl esa "/Audios/audio_nomi.mp3" manzilida mavjud bo'ladi
           
            */

            String faylga_yol = "Audios/"+Vaqtincha.audio_nomi+".mp3";
            try {
                FileInputStream inp = new FileInputStream(faylga_yol);
                Player player = new Player(inp);
                player.play();
            } catch (FileNotFoundException | JavaLayerException ex) {
                snekbar.show("Audio fayl topilmadi :(", 2300);
            }
        });

    }

}
