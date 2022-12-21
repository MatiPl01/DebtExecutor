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

import java.util.Optional;

@Component
public class SignInFormController {
    @FXML
    private InputField loginInput;

    @Autowired
    private AuthState authState;

    @FXML
    private void onSignIn() {
        String login = loginInput.getText();
        Optional<User> user = authState.signIn(login);

        if (user.isPresent()) {
            // Load the main screen if everything is successful
            SceneController.getInstance().switchScene(SceneType.MAIN);
        } else {
            // TODO - display user not found message
        }
    }
}
