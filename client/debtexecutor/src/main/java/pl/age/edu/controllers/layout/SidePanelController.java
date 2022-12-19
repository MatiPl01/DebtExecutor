package pl.age.edu.controllers.layout;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.api.group.GroupApi;
import pl.age.edu.controllers.core.ViewController;
import pl.age.edu.controllers.core.ViewType;
import pl.age.edu.controls.GroupField;
import pl.age.edu.controls.UserField;
import pl.age.edu.api.user.UserApi;
import pl.age.edu.models.Group;
import pl.age.edu.models.User;
import pl.age.edu.state.GroupState;
import pl.age.edu.state.UserState;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class SidePanelController implements Initializable {
    private static final String ACTIVE_CLASS = "active";

    @FXML
    private HBox expenseSummaryOption;

    @FXML
    private HBox expenseHistoryOption;

    @FXML
    private Button newPersonalExpenseButton;

    @FXML
    private Button newGroupExpenseButton;

    @FXML
    private Button newGroupButton;

    @FXML
    private VBox menuOptions;

    @FXML
    private VBox groupsWrapper;

    @FXML
    private VBox usersWrapper;

    @FXML
    private TitledPane groupsTitledPane;

    @FXML
    private TitledPane usersTitledPane;

    @Autowired
    private UserState userState;

    @Autowired
    private GroupState groupState;

    private ViewController viewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        addEventListeners();
    }

    @FXML
    private void onNewPersonalExpenseClick() {
        switchView(ViewType.CREATE_EXPENSE);
    }

    @FXML
    private void onNewGroupExpenseClick() {
        switchView(ViewType.CREATE_GROUP_EXPENSE);
    }

    @FXML
    private void onNewGroupClick() {
        switchView(ViewType.CREATE_GROUP);
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

    private void loadData() {
        List<User> users = UserApi.getAll();
        userState.setUsers(users);
        usersWrapper.getChildren().addAll(
                users.stream().map(UserField::new).toList()
        );

        List<Group> groups = GroupApi.getAll();
        groupState.setGroups(groups);
        groupsWrapper.getChildren().addAll(
                groups.stream().map(GroupField::new).toList()
        );
        usersTitledPane.setExpanded(true);
        groupsTitledPane.setExpanded(true);
    }

    private void addEventListeners() {
        userState.getUsers().addListener((ListChangeListener<User>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList()
                          .forEach(user -> usersWrapper.getChildren()
                                                       .add(new UserField(user)));
                }
            }
        });

        groupState.getGroups().addListener((ListChangeListener<Group>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList()
                          .forEach(group -> groupsWrapper.getChildren()
                                                    .add(new GroupField(group)));
                    }
               }
           }
       );
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
        setOptionsInactive();

        // Set the proper option in the menu active
        switch (viewType) {
            case HISTORY -> setOptionActive(expenseHistoryOption);
            case SUMMARY -> setOptionActive(expenseSummaryOption);
        }
    }
}
