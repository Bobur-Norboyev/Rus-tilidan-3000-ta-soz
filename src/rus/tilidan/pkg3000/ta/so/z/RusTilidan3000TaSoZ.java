package rus.tilidan.pkg3000.ta.so.z;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RusTilidan3000TaSoZ extends Application {
    
    @Override
    public void start ( Stage stage ) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/AsosiySahna.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image("/Image/logo.png"));
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Rus tilidan 3000 ta so'z");
        stage.setResizable(false);
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
