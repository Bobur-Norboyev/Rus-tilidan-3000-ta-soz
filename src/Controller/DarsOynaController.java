package Controller;

import BoshqaKlass.Vaqtincha;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DarsOynaController implements Initializable {

    @FXML
    private JFXHamburger hamb;
    @FXML
    private JFXTextArea matn;
    @FXML
    private Label sarlavha;
    @FXML
    private AnchorPane asosiyPanel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sarlavha.setText(Vaqtincha.darsSarlavha);
        matn.setText(Vaqtincha.darsMatni);

        asosiyPanel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.B) {
                    Node source = (Node) event.getSource();
                    Stage joriy = (Stage) source.getScene().getWindow();
                    joriy.close();
                }
            } 
        });

        HamburgerBackArrowBasicTransition hodisa = new HamburgerBackArrowBasicTransition(hamb);
        hodisa.setRate(1);
        hodisa.play();

        hamb.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node source = (Node) event.getSource();
                Stage joriy = (Stage) source.getScene().getWindow();
                joriy.close();
            }
        });

    }

}
