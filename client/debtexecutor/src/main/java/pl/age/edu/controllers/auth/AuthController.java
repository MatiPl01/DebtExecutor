package pl.age.edu.controllers.auth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import pl.age.edu.utils.SceneController;
import pl.age.edu.utils.SceneType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    private static final String SIGN_IN_FORM_PATH = "/fxml/auth/SignInForm.fxml";
    private static final String SIGN_UP_FORM_PATH = "/fxml/auth/SignUpForm.fxml";

    @FXML
    private StackPane formWrapper;

    @FXML
    private Label formChangeText;

    @FXML
    private Label formChangeHeading;

    @FXML
    private Button formChangeButton;

    private AuthFormType currentForm = AuthFormType.SIGN_IN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openSignInForm();
    }

    public void loadMainScreen() {
        SceneController.getInstance().switchScene(SceneType.MAIN);
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
        // TODO - improve text content changing
        formChangeButton.setText("SIGN UP");
        formChangeHeading.setText("Sign up");
        formChangeText.setText("Don't have an account? Press the button below to create a new one");
    }

    private void openSignUpForm() {
        openForm(SIGN_UP_FORM_PATH);
        // TODO - improve text content changing
        formChangeButton.setText("SIGN IN");
        formChangeHeading.setText("Sign in");
        formChangeText.setText("Already have an account? Press the button below to sign in");
    }

    private void openForm(String resourcePath) {
        formWrapper.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
        try {
            formWrapper.getChildren().add(loader.load());
            AuthFormController authFormController = loader.getController();
            authFormController.setParentController(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
