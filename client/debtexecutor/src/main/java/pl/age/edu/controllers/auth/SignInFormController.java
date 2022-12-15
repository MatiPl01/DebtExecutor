package pl.age.edu.controllers.auth;

import javafx.fxml.FXML;
import pl.age.edu.controls.InputField;

public class SignInFormController extends AuthFormController {
    @FXML
    private InputField loginInput;

    @FXML
    private void onSignIn() {
        // TODO - make api request to check if user with the specific login exists and fetch user data
        System.out.println(loginInput.getText());

        // Load the main screen if everything is successful
        parentController.loadMainScreen();
    }
}
