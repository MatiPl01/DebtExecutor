package pl.age.edu.controllers.layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.controllers.core.SceneController;
import pl.age.edu.controllers.core.SceneType;
import pl.age.edu.state.AuthState;
import pl.age.edu.state.UserState;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UserPanelController implements Initializable {
    @FXML
    private VBox userPanel;

    @FXML
    private Label username;

    @Autowired
    private UserState userState;

    @Autowired
    private AuthState authState;

    @FXML
    private void onLogOut() {
        authState.setLoggedInUser(null);
        // Redirect to the authentication scene
        SceneController.getInstance().switchScene(SceneType.AUTH);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closePanel();

        authState.loggedInUserProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                username.setText(newValue.getFirstName() + " " + newValue.getLastName());
            } else {
                username.setText("");
            }
        }));

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
