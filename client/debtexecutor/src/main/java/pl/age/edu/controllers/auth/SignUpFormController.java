package pl.age.edu.controllers.auth;

import javafx.fxml.FXML;
import pl.age.edu.api.group.GroupController;
import pl.age.edu.controls.InputField;

public class SignUpFormController extends AuthFormController {
    @FXML
    private InputField loginInput;

    @FXML
    private InputField firstNameInput;

    @FXML
    private InputField lastNameInput;

    @FXML
    private void onSignUp() {
        System.out.println(loginInput.getText());
        System.out.println(firstNameInput.getText());
        System.out.println(lastNameInput.getText());
        GroupController.CreateUserDTO user = new GroupController.CreateUserDTO(firstNameInput.getText(), lastNameInput.getText());

        // Load the main screen if everything is successful
        parentController.loadMainScreen();
    }
}
