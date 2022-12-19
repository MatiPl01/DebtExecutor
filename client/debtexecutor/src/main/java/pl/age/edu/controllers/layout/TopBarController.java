package pl.age.edu.controllers.layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.controllers.core.ThemeController;
import pl.age.edu.controllers.core.ThemeType;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TopBarController implements Initializable {
    @FXML
    private Pane userImage;

    @FXML
    private Button darkLightSwitch;

    private UserPanelController userPanelController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateDarkLightSwitchText();
        userImage.setOnMouseClicked(e -> openUserPanel());
    }

    @FXML
    private void onThemeChange() {
        if (ThemeController.getInstance().getCurrentTheme().equals(ThemeType.LIGHT)) {
            ThemeController.getInstance().setTheme(ThemeType.DARK);
        } else {
            ThemeController.getInstance().setTheme(ThemeType.LIGHT);
        }
        updateDarkLightSwitchText();
    }

    public void setUserPanelController(UserPanelController userPanelController) {
        this.userPanelController = userPanelController;
    }

    private void openUserPanel() {
        userPanelController.openPanel();
    }

    private void updateDarkLightSwitchText() {
        String themeName;
        if (ThemeController.getInstance().getCurrentTheme().equals(ThemeType.DARK)) {
            themeName = "light";
        } else {
            themeName = "dark";
        }

        String text = "Set " + themeName + " theme";
        darkLightSwitch.setText(text);
    }
}
