package pl.age.edu.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import pl.age.edu.controls.InputField;
import javafx.scene.control.ListView;
import pl.age.edu.models.User;

public class CreateGroupViewController {
    @FXML
    private InputField groupNameInput;

    @FXML
    private ListView<User> userSelectList;

    @FXML
    private void onSubmit() {

    }

    @FXML
    public void initialize() {
        userSelectList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(user.getFirstName() + " " + user.getLastName());
                }
            }});

        userSelectList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
