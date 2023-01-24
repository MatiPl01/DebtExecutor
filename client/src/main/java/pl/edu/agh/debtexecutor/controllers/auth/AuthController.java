package pl.edu.agh.debtexecutor.controllers.auth;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AuthController implements Initializable {
    private static final String SIGN_IN_FORM_PATH = "/fxml/auth/SignInForm.fxml";
    private static final String SIGN_UP_FORM_PATH = "/fxml/auth/SignUpForm.fxml";

    private static final SidePanelText signInText = new SidePanelText(
        "SIGN IN",
        "Sign in",
        "Already have an account? Press the button below to sign in"
    );
    private static final SidePanelText signUpText = new SidePanelText(
        "SIGN UP",
        "Sign up",
        "Don't have an account? Press the button below to create a new one"
    );

    @FXML private StackPane formWrapper;
    @FXML private Label formChangeText;
    @FXML private Label formChangeHeading;
    @FXML private Button formChangeButton;

    private AuthFormType currentForm = AuthFormType.SIGN_IN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openSignInForm();
    }

    @FXML
    private void onFormChange() {
        if (currentForm == AuthFormType.SIGN_UP) {
            openSignInForm();
            currentForm = AuthFormType.SIGN_IN;
        } else {
            openSignUpForm();
            currentForm = AuthFormType.SIGN_UP;
        }
    }

    private void openSignInForm() {
        openForm(SIGN_IN_FORM_PATH);
        formChangeButton.setText(signUpText.buttonText());
        formChangeHeading.setText(signInText.headingText());
        formChangeText.setText(signUpText.infoText());
    }

    private void openSignUpForm() {
        openForm(SIGN_UP_FORM_PATH);
        formChangeButton.setText(signInText.buttonText());
        formChangeHeading.setText(signInText.headingText());
        formChangeText.setText(signInText.infoText());
    }

    private void openForm(String resourcePath) {
        formWrapper.getChildren().clear();
        formWrapper.getChildren().add(ResourceLoader.loadFXML(resourcePath));
    }
}
