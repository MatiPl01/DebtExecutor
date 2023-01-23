package pl.edu.agh.debtexecutor.controls;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewSwitch extends HBox implements Initializable {
    private final static PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
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
        listViewButton.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);

        listViewButton.setOnAction(a -> setButtonActive(listViewButton, true));
        graphViewButton.setOnAction(a -> setButtonActive(graphViewButton, true));
    }

    public void setOnListViewClick(Runnable callback) {
        onListViewClick = callback;
    }

    public void setOnGraphViewClick(Runnable callback) {
        onGraphViewClick = callback;
    }

    public void reset() {
        setButtonActive(listViewButton, false);
    }

    private void setButtonActive(Button button, boolean execEvent) {
        if (button.getPseudoClassStates().contains(SELECTED_PSEUDO_CLASS)) return;

        Button otherButton;
        Runnable runnable;

        if (button == listViewButton) {
            otherButton = graphViewButton;
            runnable = onListViewClick;
        } else {
            otherButton = listViewButton;
            runnable = onGraphViewClick;
        }

        button.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
        otherButton.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
        if (execEvent) runnable.run();
    }
}
