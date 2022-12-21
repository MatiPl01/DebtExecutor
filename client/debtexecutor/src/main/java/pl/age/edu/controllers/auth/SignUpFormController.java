package pl.age.edu.controllers.auth;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.api.user.UserApi;
import pl.age.edu.controllers.core.SceneController;
import pl.age.edu.controllers.core.SceneType;
import pl.age.edu.controls.InputField;
import pl.age.edu.models.User;
import pl.age.edu.state.AuthState;
import pl.age.edu.state.UserState;

import java.util.Optional;

@Component
public class SignUpFormController {
    @FXML
    private InputField loginInput;

    @FXML
    private InputField firstNameInput;

    @FXML
    private InputField lastNameInput;
    @Autowired
    private AuthState authState;

    @FXML
    private void onSignUp() {
        Optional<User> user = authState.singUp(
                loginInput.getText(),
                firstNameInput.getText(),
                lastNameInput.getText()
        );

        if (user.isPresent()) {
            // Load the main screen if everything is successful
            SceneController.getInstance().switchScene(SceneType.MAIN);
        } else {
            // TODO - display error message (login is already taken)
        }
    }
}
