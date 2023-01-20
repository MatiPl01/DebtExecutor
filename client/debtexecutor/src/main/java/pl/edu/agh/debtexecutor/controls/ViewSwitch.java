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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewButton.getStyleClass().add(ACTIVE_CLASS);

        listViewButton.setOnAction(a -> setButtonActive(listViewButton));
        graphViewButton.setOnAction(a -> setButtonActive(graphViewButton));
    }

    public void setOnListViewClick(Runnable callback) {
        onListViewClick = callback;
    }

    public void setOnGraphViewClick(Runnable callback) {
        onGraphViewClick = callback;
    }

    public void reset() {
        setButtonActive(listViewButton);
    }

    private void setButtonActive(Button button) {
        if (button.getStyleClass().contains(ACTIVE_CLASS)) return;

        Button otherButton;
        Runnable runnable;

        if (button == listViewButton) {
            otherButton = graphViewButton;
            runnable = onListViewClick;
        } else {
            otherButton = listViewButton;
            runnable = onGraphViewClick;
        }

        button.getStyleClass().add(ACTIVE_CLASS);
        otherButton.getStyleClass().remove(ACTIVE_CLASS);
        runnable.run();
    }
}
