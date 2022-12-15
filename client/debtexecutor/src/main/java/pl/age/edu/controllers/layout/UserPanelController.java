package pl.age.edu.controllers.layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.age.edu.controllers.core.SceneController;
import pl.age.edu.controllers.core.SceneType;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPanelController implements Initializable {
    @FXML
    private VBox userPanel;

    @FXML
    private Label username;

    @FXML
    private void onLogOut() {
        // TODO - remove user credentials from state (think how to persist state)

        // Redirect to the authentication scene
        SceneController.getInstance().switchScene(SceneType.AUTH);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closePanel();

        // TODO - bind user name from the current user to the Label
        username.setText("Mateusz Łopaciński");

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
