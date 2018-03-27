/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc.controller;

import mc.model.FileManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Marcel
 */
public class EditorController extends GuiUtils implements Initializable {

    private final ObservableList<String> originalItems = FXCollections.observableArrayList();

    @FXML
    private TextField protocolTF;

    @FXML
    private DatePicker dateDP;

    @FXML
    private ComboBox<String> corporationCBB;

    @FXML
    private TextArea descriptionTA;

    /**
     * Limpa os campos do formulário
     */
    @FXML
    private void clearButtonAction() {
        protocolTF.clear();
        corporationCBB.getSelectionModel().clearSelection();
        descriptionTA.clear();
    }

    /**
     * Adiciona adiciona os itens do formulário no arquivo de log
     *
     * @param e Evento disparado ao clicar no botão
     */
    @FXML
    private void addLog(ActionEvent e) {
        FileInputStream fw = null;

        if (protocolTF.getText().isEmpty()) {
            information("Você não pode prosseguir sem um número de protocolo");
            protocolTF.requestFocus();
        } else if (corporationCBB.getSelectionModel().isEmpty()) {
            information("Você deve escolher uma empresa antes de continuar!");
            corporationCBB.requestFocus();
        } else if (descriptionTA.getText().isEmpty()) {
            information("Digite a descrição do evento antes de continuar!");
            descriptionTA.requestFocus();
        } else {

            if (confirmInsertion()) {
                try {
                    FileManager.saveToFile(corporationCBB.getSelectionModel().getSelectedItem(),
                            dateDP.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            protocolTF.getText(),
                            descriptionTA.getText());
                    clearButtonAction();
                } catch (FileNotFoundException ex) {
                    exceptionDialog("O arquivo de log não pode ser encontrado. Não será possível continuar!", ex);
                } catch (IOException ex) {
                    exceptionDialog("Erro no buffer", ex);
                }
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateDP.setValue(LocalDate.now());
        originalItems.addAll("Oi",
                "Tim",
                "Anatel",
                "Banco do Brasil");

        corporationCBB.setEditable(true);
        corporationCBB.getItems().addAll(originalItems);
        TextFields.bindAutoCompletion(corporationCBB.getEditor(), originalItems);

        descriptionTA.setWrapText(true);
    }

}
