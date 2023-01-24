package pl.edu.agh.debtexecutor.controllers.views.forms.createGroup;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controllers.views.forms.FormViewController;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.GroupService;
import pl.edu.agh.debtexecutor.services.UserService;
import pl.edu.agh.debtexecutor.utils.DialogUtils;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@Controller
public class CreateGroupViewController extends FormViewController {
    private static final String FORM_NAME = "Create group";

    @FXML private InputField groupNameInput;
    @FXML private ListView<User> userSelectList;

    @Autowired private UserService userService;
    @Autowired private GroupService groupService;

    @FXML
    public void initialize() {
        userService.fetchData();
        groupService.fetchData();
        List<User> users = userService.getUsers();
        setListEntries(userSelectList, users, SelectionMode.MULTIPLE);
    }

    @Override
    protected boolean validateForm() {
        GroupFormData data = getFormData();

        StringJoiner joiner = new StringJoiner("\n\t- ");
        joiner.add("");
        if (data.name().isEmpty()) joiner.add("group name cannot be empty");
        if (data.users().isEmpty()) joiner.add("select at least one user");

        if (joiner.length() > 0) {
            DialogUtils.informationDialog(
                FORM_NAME,
                "Missing data in form",
                joiner.toString()
            );
            return false;
        }

        return true;
    }

    @Override
    protected boolean showConfirmationModal() {
        GroupFormData data = getFormData();

        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Check if the following details are correct");
        joiner.add("Name: " + data.name());
        joiner.add("Members: ");
        data.users().forEach(user -> joiner.add("\t- " + user));

        Optional<ButtonType> buttonType = DialogUtils.confirmationDialog(
                FORM_NAME,
                joiner.toString()
        );

        return buttonType.map(type -> type.getButtonData().isDefaultButton())
                         .orElse(false);
    }

    @Override
    protected boolean submitForm() {
        GroupFormData data = getFormData();

        return groupService.addGroup(data.name(), data.users()).isPresent();
    }

    @Override
    protected void clearInputs() {
        groupNameInput.clear();
        userSelectList.getSelectionModel().clearSelection();
    }

    @Override
    protected void handleSubmitSuccess() {
        DialogUtils.informationDialog(
            FORM_NAME,
            "Success!",
            "Group has been successfully created"
        );
    }

    private GroupFormData getFormData() {
        return new GroupFormData(
            groupNameInput.getText(),
            userSelectList.getSelectionModel().getSelectedItems()
        );
    }
}
