package pl.edu.agh.debtexecutor.controllers.layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controllers.core.ThemeController;
import pl.edu.agh.debtexecutor.controllers.core.ThemeType;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TopBarController implements Initializable {
    private final static String LIGHT_THEME_NAME = "light";
    private final static String DARK_THEME_NAME = "dark";

    @FXML private Pane userImage;
    @FXML private Button darkLightSwitch;

    @Autowired private ThemeController themeController;

    private UserPanelController userPanelController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateDarkLightSwitchText();
        userImage.setOnMouseClicked(e -> userPanelController.togglePanel());
    }

    @FXML
    private void onThemeChange() {
        if (themeController.getCurrentTheme().equals(ThemeType.LIGHT)) {
            themeController.setTheme(ThemeType.DARK);
        } else {
            themeController.setTheme(ThemeType.LIGHT);
        }
        updateDarkLightSwitchText();
    }

    public void setUserPanelController(UserPanelController userPanelController) {
        this.userPanelController = userPanelController;
    }

    private void updateDarkLightSwitchText() {
        String themeName;
        if (themeController.getCurrentTheme().equals(ThemeType.DARK)) {
            themeName = LIGHT_THEME_NAME;
        } else {
            themeName = DARK_THEME_NAME;
        }

        String text = "Set " + themeName + " theme";
        darkLightSwitch.setText(text);
    }
}
