package pl.age.edu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SidePanelController implements Initializable {
    @FXML
    public HBox test;

    @FXML
    private void onNewExpenseClick(ActionEvent actionEvent) {
        // TODO - open new expense form
    }

    @FXML
    private void onTabSelect(MouseEvent mouseEvent) {
        HBox hbox = (HBox) mouseEvent.getSource();

        switch (hbox.getId()) {
            case "summary-tab" -> System.out.println("SUMMARY");
            case "history-tab" -> System.out.println("HISTORY");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
