package pl.age.edu.controllers.views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.api.group.GroupApi;
import pl.age.edu.api.group.dto.CreateGroupDTO;
import pl.age.edu.controls.InputField;
import pl.age.edu.models.Group;
import pl.age.edu.models.User;
import pl.age.edu.state.GroupState;
import pl.age.edu.state.UserState;

import java.util.List;
import java.util.Optional;

@Component
public class CreateGroupViewController {
    @FXML
    private InputField groupNameInput;

    @FXML
    private ListView<User> userSelectList;

    @Autowired
    private UserState userState;

    @Autowired
    private GroupState groupState;

    @FXML
    private void onSubmit() {
        String groupName = groupNameInput.getText();
        List<String> users = userSelectList.getSelectionModel()
                .getSelectedItems()
                .stream()
                .map(User::getId)
                .toList();
        CreateGroupDTO dto = new CreateGroupDTO(groupName, users);
        Optional<Group> group = GroupApi.createGroup(dto);
        group.ifPresent(value -> groupState.addGroup(value));
    }

    @FXML
    public void initialize() {
        // TODO - maybe replace with API request to keep data updated
        ObservableList<User> users = userState.getUsers();
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
