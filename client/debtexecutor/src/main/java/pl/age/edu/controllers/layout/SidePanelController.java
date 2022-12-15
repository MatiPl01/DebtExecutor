package pl.age.edu.controllers.layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.age.edu.api.group.GroupController;
import pl.age.edu.controllers.core.ViewController;
import pl.age.edu.controllers.core.ViewType;
import pl.age.edu.controls.GroupField;
import pl.age.edu.controls.UserField;
import pl.age.edu.models.Group;
import pl.age.edu.models.User;
import pl.age.edu.api.user.UserController;

import java.net.URL;
import java.util.List;
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
    private Button newUserButton;
    @FXML
    private Button newGroupButton;
    @FXML
    private VBox menuOptions;

    @FXML
    private VBox groupsWrapper;

    @FXML
    private VBox usersWrapper;

    private ViewController viewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<User> users = UserController.getAll();

        users.forEach(user -> usersWrapper.getChildren().add(new UserField(user)));

        List<Group> groups = GroupController.getAll();

        groups.forEach(group -> groupsWrapper.getChildren().add(new GroupField(group)));
    }

    @FXML
    private void onNewExpenseClick() {
        viewController.switchView(ViewType.CREATE_EXPENSE);
    }

    @FXML
    private void onNewUserClick() {
        viewController.switchView(ViewType.CREATE_USER);
    }

    @FXML
    private void onNewGroupClick() {
        viewController.switchView(ViewType.CREATE_GROUP);
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
