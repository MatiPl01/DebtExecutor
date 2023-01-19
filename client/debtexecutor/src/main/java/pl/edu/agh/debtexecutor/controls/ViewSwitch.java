package pl.edu.agh.debtexecutor.controls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewSwitch extends HBox implements Initializable {
    private final static String ACTIVE_CLASS = "active";
    private static final String FXML_PATH = "/fxml/controls/ViewSwitch.fxml";

    private Runnable onListViewClick;
    private Runnable onGraphViewClick;

    @FXML private Button listViewButton;
    @FXML private Button graphViewButton;

    public ViewSwitch() {
        ResourceLoader.loadControlFXML(FXML_PATH, this);
    }

    public void setOnListViewClick(Runnable callback) {
        onListViewClick = callback;
    }

    public void setOnGraphViewClick(Runnable callback) {
        onGraphViewClick = callback;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewButton.getStyleClass().add(ACTIVE_CLASS);

        listViewButton.setOnAction(a -> {
            if (listViewButton.getStyleClass().contains(ACTIVE_CLASS)) return;
            graphViewButton.getStyleClass().remove(ACTIVE_CLASS);
            listViewButton.getStyleClass().add(ACTIVE_CLASS);
            onListViewClick.run();
        });
        graphViewButton.setOnAction(a -> {
            if (graphViewButton.getStyleClass().contains(ACTIVE_CLASS)) return;
            listViewButton.getStyleClass().remove(ACTIVE_CLASS);
            graphViewButton.getStyleClass().add(ACTIVE_CLASS);
            onGraphViewClick.run();
        });
    }
}
