/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Marcel
 */
public class ViewerController extends GuiUtils implements Initializable {

    private final List<String> strList;
    private final ObservableList<Protocol> protOL = FXCollections.observableArrayList();

    public ViewerController(List<String> strList) {
        this.strList = strList;
    }

    @FXML
    private ListView<Protocol> protocolsLV;

    @FXML
    private Pagination pagination;

    @FXML
    private TextField corpTF;

    @FXML
    private TextField protocolTF;

    @FXML
    private TextArea descTA;

    @FXML
    private TextField dateTF;

    @FXML
    private Group group;

    public void fillForm(Protocol p) {
        corpTF.setText(p.getCorp());
        dateTF.setText(p.getDate());
        descTA.setText(p.desc);
        protocolTF.setText(p.prot);

    }

    public void fillProtOL() {
        for (String s : strList) {
            String[] array = s.split(";");
            protOL.add(new Protocol(array[0], array[2], array[3], array[1]));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillProtOL();
        protocolsLV.setItems(protOL);

        protocolsLV.getSelectionModel()
                .selectedItemProperty().addListener(
                        (ObservableValue<? extends Protocol> observable,
                                Protocol oldValue, Protocol newValue) -> {
                            fillForm(newValue);
                        });
        protocolsLV.getSelectionModel().selectedIndexProperty()
                .addListener((ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) -> {
                    pagination.setCurrentPageIndex(newValue.intValue());
                });
        pagination.setPageCount(protOL.size());
        pagination.currentPageIndexProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    protocolsLV.getSelectionModel().select(newValue.intValue());
                });

        // protocolsLV.selectionModelProperty().bind(null);
        //  pagination.currentPageIndexProperty().bind(protocolsLV.getSelectionModel().selectedIndexProperty());
    }

    private class Protocol {

        private final String corp;
        private final String prot;
        private final String desc;
        private final String date;

        public Protocol(String corp, String prot, String desc, String date) {
            this.corp = corp;
            this.prot = prot;
            this.desc = desc;
            this.date = date;
        }

        public String getCorp() {
            return corp;
        }

        public String getProt() {
            return prot;
        }

        public String getDesc() {
            return desc;
        }

        public String getDate() {
            return date;
        }

        @Override
        public String toString() {
            return String.format("%16s - %-12s", corp, date);
        }
    }
}
