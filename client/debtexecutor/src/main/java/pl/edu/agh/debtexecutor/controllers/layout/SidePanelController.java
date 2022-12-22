package pl.edu.agh.debtexecutor.controllers.layout;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controllers.core.ViewController;
import pl.edu.agh.debtexecutor.controllers.core.ViewType;
import pl.edu.agh.debtexecutor.controls.GroupField;
import pl.edu.agh.debtexecutor.controls.UserField;
import pl.edu.agh.debtexecutor.models.Group;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.GroupService;
import pl.edu.agh.debtexecutor.services.UserService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SidePanelController implements Initializable {
    private static final String ACTIVE_CLASS = "active";

    @FXML private HBox expenseSummaryOption;
    @FXML private HBox expenseHistoryOption;
    @FXML private Button newPersonalExpenseButton;
    @FXML private Button newGroupExpenseButton;
    @FXML private Button newGroupButton;
    @FXML private VBox menuOptions;
    @FXML private VBox groupsWrapper;
    @FXML private VBox usersWrapper;
    @FXML private TitledPane groupsTitledPane;
    @FXML private TitledPane usersTitledPane;

    @Autowired private UserService userService;
    @Autowired private GroupService groupService;

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
        userService.fetchData();
        groupService.fetchData();

        usersWrapper.getChildren().setAll(
                userService.getUsers().stream().map(UserField::new).toList()
        );

        groupsWrapper.getChildren().setAll(
                groupService.getGroups().stream().map(GroupField::new).toList()
        );

        usersTitledPane.setExpanded(true);
        groupsTitledPane.setExpanded(true);
    }

    private void addEventListeners() {
        // TODO - refactor repeated code below
        userService.getUsers().addListener((ListChangeListener<User>) change -> {
            while (change.next()) {
                ObservableList<Node> children = usersWrapper.getChildren();

                if (change.wasReplaced()) {
                    for (int i = change.getFrom(); i < change.getTo(); i++) {
                        if (i < children.size()) {
                            children.set(i, new UserField(change.getAddedSubList().get(i)));
                        } else {
                            children.add(i, new UserField(change.getAddedSubList().get(i)));
                        }
                    }
                } else if (change.wasRemoved()) {
                    children.remove(change.getFrom(), change.getTo());
                } else if (change.wasAdded()) {
                    children.addAll(change.getAddedSubList().stream().map(UserField::new).toList());
                }
            }
        });

        groupService.getGroups().addListener((ListChangeListener<Group>) change -> {
            while (change.next()) {
                ObservableList<Node> children = groupsWrapper.getChildren();

                if (change.wasReplaced()) {
                    for (int i = change.getFrom(); i < change.getTo(); i++) {
                        if (i < children.size()) {
                            children.set(i, new GroupField(change.getAddedSubList().get(i)));
                        } else {
                            children.add(i, new GroupField(change.getAddedSubList().get(i)));
                        }
                    }
                } else if (change.wasRemoved()) {
                    children.remove(change.getFrom(), change.getTo());
                } else if (change.wasAdded()) {
                    children.addAll(change.getAddedSubList().stream().map(GroupField::new).toList());
                }
            }
        });
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
