package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutController implements Initializable {
    
    @FXML
    private Button ok;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Node source = (Node) event.getSource();
                Stage joriy = (Stage) source.getScene().getWindow();
                joriy.close();
            }
        });
        
    }    
    
}
