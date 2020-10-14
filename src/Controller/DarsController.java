package Controller;

import BoshqaKlass.Vaqtincha;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXToolbar;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DarsController implements Initializable {

    @FXML
    private JFXToolbar tepaQism;
    @FXML
    private JFXHamburger hamb;
    private HamburgerBackArrowBasicTransition hodisa;
    @FXML
    private AnchorPane asosiyPanel;
    @FXML
    private JFXButton bir, ikki, uch, turt, besh, olti, yetti, sakkiz, tuqqiz, un, unbir, unikki;
    @FXML
    private JFXButton yordam;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hodisa = new HamburgerBackArrowBasicTransition(hamb);
        hodisa.setRate(1);
        hodisa.play();

        JFXSnackbar snekbar = new JFXSnackbar(asosiyPanel);
        String stil = NatijaController.class.getClass().getResource("/Css/snackbar.css").toExternalForm();
        snekbar.getStylesheets().add(stil);

        yordam.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                snekbar.show("Bu bo'limdagi darslar yordamida siz \n Rus tili grammatikasini o'rganasiz.", 4200);
            }
        });

        asosiyPanel.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.B) {
                chiqishHodisasi(event);
            }
        });

        hamb.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                chiqishHodisasi(event);
            }
        });
    }

    public void hodisacha(Event e) {
        
        if (e.getSource().equals(bir)) {
            Vaqtincha.darsniOzgartir("bir.txt", "1 - dars");
        } else if (e.getSource().equals(ikki)) {
            Vaqtincha.darsniOzgartir("ikki.txt", "2 - dars");
        } else if (e.getSource().equals(uch)) {
            Vaqtincha.darsniOzgartir("uch.txt", "3 - dars");
        } else if (e.getSource().equals(turt)) {
            Vaqtincha.darsniOzgartir("turt.txt", "4 - dars");
        } else if (e.getSource().equals(besh)) {
            Vaqtincha.darsniOzgartir("besh.txt", "5 - dars");
        } else if (e.getSource().equals(olti)) {
            Vaqtincha.darsniOzgartir("olti.txt", "6 - dars");
        } else if (e.getSource().equals(yetti)) {
            Vaqtincha.darsniOzgartir("yetti.txt", "7 - dars");
        } else if (e.getSource().equals(sakkiz)) {
            Vaqtincha.darsniOzgartir("sakkiz.txt", "8 - dars");
        } else if (e.getSource().equals(tuqqiz)) {
            Vaqtincha.darsniOzgartir("tuqqiz.txt", "9 - dars");
        } else if (e.getSource().equals(un)) {
            Vaqtincha.darsniOzgartir("un.txt", "10 - dars");
        } else if (e.getSource().equals(unbir)) {
            Vaqtincha.darsniOzgartir("unbir.txt", "11 - dars");
        } else if (e.getSource().equals(unikki)) {
            Vaqtincha.darsniOzgartir("unikki.txt", "12 - dars");
        }

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/darsOyna.fxml"));
        } catch (IOException ex) {
            System.out.println("yuklanmadi");
        }
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("/Image/logo.png"));
        stage.show();

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
