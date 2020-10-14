package Controller;

import BoshqaKlass.Vaqtincha;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AsosiySahnaController implements Initializable {

    @FXML
    private JFXHamburger tugmacha;
    @FXML
    private JFXListView<Label> bolimlar;
    @FXML
    private JFXToolbar tepaQism;
    @FXML
    private JFXDrawer tortma;
    @FXML
    private AnchorPane asosiyPanel;

    private HamburgerBackArrowBasicTransition hodisa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VBox box = null;
        try {
            box = FXMLLoader.load(getClass().getResource("/FXML/menyu.fxml"));
        } catch (IOException ex) {
            System.out.println("Menyu yuklanmadi");
        }
        tortma.setSidePane(box);
        tortma.setOverLayVisible(false);

        for (Node avlod : box.getChildren()) {
            avlod.addEventHandler ( MouseEvent.MOUSE_CLICKED, ( e ) -> {
                if (avlod.getAccessibleText() != null) {
                    switch (avlod.getAccessibleText()) {
                        case "Uchming":
                            menyuHodisasi(avlod.getAccessibleText());
                            break;
                        case "Dars":
                            menyuHodisasi(avlod.getAccessibleText());
                            break;
                        case "Izlash":
                            menyuHodisasi(avlod.getAccessibleText());
                            break;
                        case "Tanlanganlar":
                            menyuHodisasi(avlod.getAccessibleText());
                            break;
                        case "Yordam":
                            menyuHodisasi(avlod.getAccessibleText());
                            break;
                        case "Haqida":
                            menyuHodisasi(avlod.getAccessibleText());
                            break;
                        case "Chiqish":
                            menyuHodisasi(avlod.getAccessibleText());
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        Label l1 = new Label("            Ot");
        l1.setGraphic(new ImageView(new Image("/Image/bookmark.png")));
        l1.setFont(new Font("Times new Roman", 50));
        Label l2 = new Label("            Sifat");
        l2.setGraphic(new ImageView(new Image("/Image/bookmark.png")));
        l2.setFont(new Font("Times new Roman", 50));
        Label l4 = new Label("            Fe'l");
        l4.setGraphic(new ImageView(new Image("/Image/bookmark.png")));
        l4.setFont(new Font("Times new Roman", 50));
        bolimlar.getItems().addAll(l1, l2, l4);
        bolimlar.setExpanded(true);
        bolimlar.depthProperty().set(1);

        //Tepa hamburger ishlash hodisasi to'liq yozib ketildi.... 
        hodisa = new HamburgerBackArrowBasicTransition(tugmacha);
        hodisa.setRate(-1);
        tugmacha.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            hodisa.setRate(hodisa.getRate() * (-1));
            hodisa.play();
            if (!tortma.isOpened()) {
                tortma.open();
            } else {
                tortma.close();
            }


        });
        try {
            bolimlar.getStylesheets().add("/Css/listview_stil.css");
        } catch (Exception e) {
            System.out.println("xatolik");
        }

        //listView ga harakat berish kodlari yozib ketilyapti.
        bolimlar.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    harakatgaKeltir(event);
                } catch (IOException ex) {
                    System.out.println("Yuklab bo'lmadi");
                }
            }
        });

        bolimlar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (bolimlar.getSelectionModel().getSelectedIndex() >= 0) {
                    int k = bolimlar.getSelectionModel().getSelectedIndex();

                    switch (k) {
                        case 0:
                            Vaqtincha.bolimAlmashtir("Ot", 1);
                            break;
                        case 1:
                            Vaqtincha.bolimAlmashtir("Sifat", 2);
                            break;
                        case 2:
                            Vaqtincha.bolimAlmashtir("Fe'l", 3);
                            break;
                    }
                    Node source = (Node) e.getSource();
                    Stage joriy = (Stage) source.getScene().getWindow();
                    joriy.close();
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/SuzRoyhat.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(AsosiySahnaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stage.setScene(new Scene(root));
                    stage.getIcons().add(new Image("/Image/logo.png"));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) e.getSource()).getScene().getWindow());
                    stage.setResizable(false);
                    String satr = Vaqtincha.bolimNomi + " so'z turkumi";
                    stage.setTitle(satr);
                    stage.show();
                }
            }
        });

        asosiyPanel.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.B) {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
        
    }

    public void harakatgaKeltir(KeyEvent e) throws IOException {
        int k = bolimlar.getSelectionModel().getSelectedIndex();
        if (k >= 0) {
            switch (k) {
                case 0:
                    Vaqtincha.bolimAlmashtir("Ot", 1);
                    break;
                case 1:
                    Vaqtincha.bolimAlmashtir("Sifat", 2);
                    break;
                case 2:
                    Vaqtincha.bolimAlmashtir("Fe'l", 3);
                    break;
            }
            Node source = (Node) e.getSource();
            Stage joriy = (Stage) source.getScene().getWindow();
            joriy.close();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SuzRoyhat.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) e.getSource()).getScene().getWindow());
            stage.setResizable(false);
            stage.getIcons().add(new Image("/Image/logo.png"));
            stage.show();

        }
    }

    private void menyuHodisasi(String satr) {

        switch (satr) {
            case "Uchming":
                tortma.close();
                hodisa.setRate(hodisa.getRate() * (-1));
                hodisa.play();
                break;
            case "Dars":
                yopibOch("Dars", "Grammatika darslari");
                break;
            case "Izlash":
                yopibOch("Izlash", "So'z izlash");
                break;
            case "Tanlanganlar": {
                tortma.close();
                hodisa.setRate(hodisa.getRate() * (-1));
                hodisa.play();
                JFXSnackbar snekbar = new JFXSnackbar(asosiyPanel);
                String stil = NatijaController.class.getClass().getResource("/Css/snackbar.css").toExternalForm();
                snekbar.getStylesheets().add(stil);
                snekbar.show("\"Test\" bo'limi tez kunda, ya'ni dasturimizning \nkeyingi versiyalarida taqdim etiladi. Bizni kuzatib boring.", 6000);
            }
            break;
            case "Yordam": {
                Vaqtincha.darsniOzgartir("yordam.txt", "Yordam");
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

            break;
            case "Haqida": {
            Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/about.fxml"));
                } catch (IOException ex) {
                    System.out.println("yuklanmadi");
                }
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.getIcons().add(new Image("/Image/logo.png"));
                stage.setResizable(false);
                stage.show();
            }
                break;
            case "Chiqish":
                System.exit(0);
                break;
        }
    }

    public void yopibOch(String faylNomi, String sarlavha) {

        tortma.close();
        hodisa.setRate(hodisa.getRate() * (-1));
        hodisa.play();

        Stage joriy = (Stage) tepaQism.getScene().getWindow();
        joriy.close();

        Stage stage = new Stage();
        Parent root = null;
        String satr = "/FXML/" + faylNomi + ".fxml";
        try {
            root = FXMLLoader.load(getClass().getResource(satr));
        } catch (IOException ex) {
            System.out.println("yuklab bulmadi");
        }
        stage.setScene(new Scene(root));
        stage.setTitle(sarlavha);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/Image/logo.png"));
        stage.show();
    }

    public String helpText() {
        String satr = "";
        return satr;
    }

}
