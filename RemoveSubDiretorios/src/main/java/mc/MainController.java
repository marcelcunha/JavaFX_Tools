package mc;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class MainController implements Initializable {

    private File f;
    private final Diretorios dirs = new Diretorios();

    @FXML
    private TextField caminhoTF;

    @FXML
    private TextArea saidaTA;

    @FXML
    void entradaBtnAction(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();

        f = dc.showDialog(null);
        caminhoTF.setText(f.getAbsolutePath());
    }

    @FXML
    void iniciarBtnAction(ActionEvent event) {
        if (dirs.validaEntrada(f)) {
            dirs.ajustaDiretorio(f);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                    saidaTA.setText(dirs.getStr());
                    Thread.sleep(500);

                } catch (InterruptedException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

}
