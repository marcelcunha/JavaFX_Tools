/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * @author Marcel
 */
public abstract class GuiUtils {
    /**
     * Exibe um dialog com o texto do parâmetro
     *
     * @param text Texto a ser exibido no corpo do dialog
     */
    public void information(String text) {
        Alert a = new Alert(Alert.AlertType.INFORMATION,
                text);
        a.showAndWait();
    }

    /**
     * Exibe um dialog confirmando a insersão no arquivo
     *
     * @return @code{True}, se o usuário escolher Sim, False, caso contrário
     */
    public boolean confirmInsertion() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,
                "Deseja inserir no LOG?", ButtonType.YES, ButtonType.NO);
        a.setTitle("Confirmação");
        a.showAndWait();

        return a.getResult() == ButtonType.YES;
    }

    public static void exceptionDialog(String mensagem, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro foi encontrado!");
        alert.setContentText(mensagem);

        Label label = new Label("Stack Trace da excessão(Para o desenvolvedor):");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String content = sw.toString();

        TextArea textArea = new TextArea(content);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane gp = new GridPane();
        gp.setMaxWidth(Double.MAX_VALUE);
        gp.add(label, 0, 0);
        gp.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(gp);

        alert.showAndWait();
    }

}
