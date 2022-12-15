package pl.age.edu.controllers.layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.age.edu.controllers.core.ViewController;
import pl.age.edu.controllers.core.ViewType;
import pl.age.edu.controls.GroupField;
import pl.age.edu.controls.UserField;
import pl.age.edu.models.Group;
import pl.age.edu.models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SidePanelController implements Initializable {
    private static final String ACTIVE_CLASS = "active";

    @FXML
    private HBox expenseSummaryOption;

    @FXML
    private HBox expenseHistoryOption;

    @FXML
    private Button newExpenseButton;

    @FXML
    private VBox menuOptions;

    @FXML
    private VBox groupsWrapper;

    @FXML
    private VBox usersWrapper;

    private ViewController viewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO - bind generating labels to data
        groupsWrapper.getChildren()
                     .addAll(
                             new GroupField(new Group()),
                             new GroupField(new Group()),
                             new GroupField(new Group())
                     );

        usersWrapper.getChildren()
                    .addAll(new UserField(new User()),
                            new UserField(new User()),
                            new UserField(new User())
                    );
    }

    @FXML
    private void onNewExpenseClick() {
        viewController.switchView(ViewType.CREATE_EXPENSE);
    }

    @FXML
    private void onTabSelect(MouseEvent mouseEvent) {
        // TODO - improve view switching
        if (mouseEvent.getSource().equals(expenseSummaryOption)) {
            switchView(ViewType.SUMMARY);
        } else if (mouseEvent.getSource().equals(expenseHistoryOption)) {
            switchView(ViewType.HISTORY);
        }
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
        switchView(viewController.getActiveView());
    }

    private void setOptionActive(HBox option) {
        setOptionsInactive();
        option.getStyleClass().add(ACTIVE_CLASS);
    }

    private void setOptionsInactive() {
        menuOptions.getChildren().forEach(child -> {
            child.getStyleClass().remove(ACTIVE_CLASS);
        });
    }

    private void switchView(ViewType viewType) {
        viewController.switchView(viewType);

        // Set the proper option in the menu active
        switch (viewType) {
            case CREATE_EXPENSE, CREATE_GROUP -> setOptionsInactive();
            case HISTORY -> setOptionActive(expenseHistoryOption);
            case SUMMARY -> setOptionActive(expenseSummaryOption);
        }
    }
}
