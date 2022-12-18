package pl.age.edu.controllers.auth;

import javafx.fxml.FXML;
import pl.age.edu.api.user.UserController;
import pl.age.edu.controls.InputField;
import pl.age.edu.models.User;

import java.util.Optional;

public class SignUpFormController extends AuthFormController {
    @FXML
    private InputField loginInput;

    @FXML
    private InputField firstNameInput;

    @FXML
    private InputField lastNameInput;

    @FXML
    private void onSignUp() {
        Optional<User> user = UserController.singUp(
                loginInput.getText(),
                firstNameInput.getText(),
                lastNameInput.getText()
        );

        System.out.println(">>>>");
        System.out.println(user);
        System.out.println(loginInput.getText());
        System.out.println(firstNameInput.getText());
        System.out.println(lastNameInput.getText());

        if (user.isPresent()) {
            // Load the main screen if everything is successful
            parentController.loadMainScreen();
        } else {
            // TODO - display error message (login is already taken)
        }
    }
}
