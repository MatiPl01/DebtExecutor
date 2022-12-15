package pl.age.edu.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import pl.age.edu.controls.HistoryItem;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryViewController implements Initializable {
    @FXML
    private VBox historyWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        historyWrapper.getChildren()
                      .addAll(new HistoryItem(),
                              new HistoryItem(),
                              new HistoryItem());
    }
}
