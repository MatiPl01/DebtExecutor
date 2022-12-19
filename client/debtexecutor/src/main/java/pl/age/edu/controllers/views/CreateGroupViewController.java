package pl.age.edu.controllers.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import org.springframework.stereotype.Component;
import pl.age.edu.api.group.dto.CreateGroupDTO;
import pl.age.edu.api.group.GroupApi;
import pl.age.edu.api.user.UserApi;
import pl.age.edu.controls.InputField;
import javafx.scene.control.ListView;
import pl.age.edu.models.User;

import java.util.List;

@Component
public class CreateGroupViewController {
    @FXML
    private InputField groupNameInput;

    @FXML
    private ListView<User> userSelectList;

    @FXML
    private void onSubmit() {
        String groupName = groupNameInput.getText();
        List<String> users = userSelectList.getSelectionModel()
                .getSelectedItems()
                .stream()
                .map(User::getId)
                .toList();
        CreateGroupDTO dto = new CreateGroupDTO(groupName, users);
        GroupApi.add(dto);
    }

    @FXML
    public void initialize() {
        List<User> allUsers = UserApi.getAll();

        ObservableList<User> users = FXCollections.observableArrayList(allUsers);


        userSelectList.setItems(users);
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
