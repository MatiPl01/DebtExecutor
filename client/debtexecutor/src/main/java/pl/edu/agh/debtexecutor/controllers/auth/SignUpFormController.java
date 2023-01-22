package pl.edu.agh.debtexecutor.controllers.auth;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controllers.core.SceneController;
import pl.edu.agh.debtexecutor.controllers.core.SceneType;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.AuthService;

import java.util.Optional;

@Controller
public class SignUpFormController {
    @FXML
    private InputField loginInput;

    @FXML
    private InputField firstNameInput;

    @FXML
    private InputField lastNameInput;

    @Autowired
    private AuthService authService;

    @Autowired
    private SceneController sceneController;

    @FXML
    private void onSignUp() {
        Optional<User> user = authService.singUp(
            loginInput.getText(),
            firstNameInput.getText(),
            lastNameInput.getText()
        );

        if (user.isPresent()) {
            clearInput();
            // Load the main screen if everything is successful
            sceneController.switchScene(SceneType.MAIN);
        } else {
            // TODO - display error message (login is already taken)
        }
    }

    private void clearInput() {
        loginInput.setText("");
        firstNameInput.setText("");
        lastNameInput.setText("");
    }
}
