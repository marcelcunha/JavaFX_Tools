package mc;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class MainController implements Observer, Initializable {

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
        dirs.addObserver(this);
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof Diretorios) {
            saidaTA.appendText(dirs.getStr());
        }

    }

}
