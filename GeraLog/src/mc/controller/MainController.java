/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc.controller;

import static mc.controller.GuiUtils.exceptionDialog;
import mc.model.FileManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Marcel
 */
public class MainController extends GuiUtils implements Initializable {

    @FXML
    AnchorPane aPane;

    @FXML
    Tab viewTAB;
    
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void loadViewer() {
        aPane.getChildren().clear();

        FXMLLoader loader = new FXMLLoader();
        try {
            if (!FileManager.loadFromFile().isEmpty()) {
                ViewerController controller = new ViewerController(
                        FileManager.loadFromFile());
                loader.setController(controller);
                loader.load(getClass().getResource("/mc/view/viewer.fxml")
                        .openStream());

                aPane.getChildren().add(loader.getRoot());
            } else {
                information("Não há protocolos cadastrados. Cadastre um novo antes de abrir essa Tab!");
            }
        } catch (FileNotFoundException ex) {
            exceptionDialog(ex.getMessage() + "Portanto não há como acessar essa sessão do programa!", ex);
        } catch (IOException ex) {
            exceptionDialog("Erro ao ler o arquivo", ex);
            ex.printStackTrace();
        }
    }

}
