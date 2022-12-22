package pl.edu.agh.debtexecutor.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.GroupService;
import pl.edu.agh.debtexecutor.services.UserService;

import java.util.List;

@Component
public class CreateGroupViewController extends FormViewController {
    @FXML private InputField groupNameInput;
    @FXML private ListView<User> userSelectList;

    @Autowired private UserService userService;
    @Autowired private GroupService groupService;

    @FXML
    private void onSubmit() {
        String groupName = groupNameInput.getText();
        List<User> users = userSelectList.getSelectionModel().getSelectedItems();
        groupService.addGroup(groupName, users);
        // TODO - add info modal that expense was created
    }

    @FXML
    public void initialize() {
        userService.fetchData();
        groupService.fetchData();
        List<User> users = userService.getUsers();
        setListEntries(userSelectList, users, SelectionMode.MULTIPLE);
    }
}
