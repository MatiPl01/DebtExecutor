package pl.edu.agh.debtexecutor.controllers.layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controllers.core.SceneController;
import pl.edu.agh.debtexecutor.controllers.core.SceneType;
import pl.edu.agh.debtexecutor.services.AuthService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UserPanelController implements Initializable {
    @FXML private VBox userPanel;
    @FXML private Label username;

    @Autowired private AuthService authService;
    @Autowired private SceneController sceneController;

    @FXML
    private void onLogOut() {
        authService.setLoggedInUser(null);
        // Redirect to the authentication scene
        sceneController.switchScene(SceneType.AUTH);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closePanel();
        authService.getLoggedInUser()
                   .ifPresent(user -> username.setText(user.toString()));

        authService.loggedInUserProperty()
                   .addListener((observable, oldValue, newValue) -> {
                       username.setText(newValue == null ?
                                        "" :
                                        newValue.toString());
                   });

        // Close the user profile panel on mouse leave
        userPanel.setOnMouseExited(e -> closePanel());
    }

    public void openPanel() {
        userPanel.setVisible(true);
        userPanel.setManaged(true);
    }

    public void closePanel() {
        userPanel.setVisible(false);
        userPanel.setManaged(false);
    }
}
