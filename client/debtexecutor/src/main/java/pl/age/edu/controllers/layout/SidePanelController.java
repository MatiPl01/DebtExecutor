package pl.age.edu.controllers.layout;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import pl.age.edu.controllers.core.ViewController;
import pl.age.edu.controllers.core.ViewType;

public class SidePanelController {
    private ViewController viewController;

    @FXML
    Button newExpenseButton;

    @FXML
    private void onNewExpenseClick() {
        viewController.switchView(ViewType.CREATE_EXPENSE);
    }

    @FXML
    private void onTabSelect(MouseEvent mouseEvent) {
        HBox hbox = (HBox) mouseEvent.getSource();

        switch (hbox.getId()) {
            case "summary-tab" -> viewController.switchView(ViewType.SUMMARY);
            case "history-tab" -> viewController.switchView(ViewType.HISTORY);
        }
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }
}
