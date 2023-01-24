package pl.edu.agh.debtexecutor.controllers.auth;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controllers.core.SceneController;
import pl.edu.agh.debtexecutor.controllers.core.SceneType;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.AuthService;
import pl.edu.agh.debtexecutor.utils.DialogUtils;

import java.util.Optional;

@Controller
public class SignInFormController {
    @FXML
    private InputField loginInput;

    @Autowired
    private AuthService authService;
    @Autowired
    private SceneController sceneController;

    @FXML
    private void onSignIn() {
        String login = loginInput.getText();
        Optional<User> user = authService.signIn(login);

        if (user.isPresent()) {
            clearInput();
            // Load the main screen if everything is successful
            sceneController.switchScene(SceneType.MAIN);
        }
    }

    private void clearInput() {
        loginInput.clear();
    }
}
