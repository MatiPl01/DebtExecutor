package pl.age.edu.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import pl.age.edu.controls.SummaryItem;

import java.net.URL;
import java.util.ResourceBundle;

public class SummaryViewController implements Initializable {
    @FXML
    private VBox summariesWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        summariesWrapper.getChildren()
                      .addAll(new SummaryItem(),
                              new SummaryItem(),
                              new SummaryItem());
    }
}
