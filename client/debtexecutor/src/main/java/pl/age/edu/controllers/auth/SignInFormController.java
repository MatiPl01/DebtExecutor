package pl.age.edu.controllers.auth;

import javafx.fxml.FXML;
import pl.age.edu.api.user.UserController;
import pl.age.edu.controls.InputField;
import pl.age.edu.models.User;

import java.util.Optional;

public class SignInFormController extends AuthFormController {
    @FXML
    private InputField loginInput;

    @FXML
    private void onSignIn() {
        String login = loginInput.getText();

        // TODO - store user in the global state
        Optional<User> user = UserController.signIn(login);

        if (user.isPresent()) {
            // Load the main screen if everything is successful
            parentController.loadMainScreen();
        } else {
            // TODO - display user not found message
        }
    }
}
