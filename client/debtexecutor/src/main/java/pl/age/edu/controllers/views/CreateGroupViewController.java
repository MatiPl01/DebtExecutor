package pl.age.edu.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.controls.InputField;
import pl.age.edu.models.User;
import pl.age.edu.state.GroupState;
import pl.age.edu.state.UserState;

import java.util.List;

@Component
public class CreateGroupViewController extends FormViewController {
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
        List<User> users = userSelectList.getSelectionModel().getSelectedItems();
        groupState.addGroup(groupName, users);
    }

    @FXML
    public void initialize() {
        userState.fetchData();
        groupState.fetchData();
        setListEntries(userSelectList, userState.getUsers());
        // TODO - add info modal that expense was created
    }
}
