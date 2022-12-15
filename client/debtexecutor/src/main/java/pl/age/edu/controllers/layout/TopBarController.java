package pl.age.edu.controllers.layout;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import pl.age.edu.controllers.core.ThemeController;
import pl.age.edu.controllers.core.ThemeType;

public class TopBarController {
    private final ThemeController themeController = ThemeController.getInstance();

    @FXML
    private Pane userImage;

    @FXML
    private Button darkLightSwitch;

    private UserPanelController userPanelController;

    @FXML
    private void initialize() {
        userImage.setOnMouseClicked(e -> openUserPanel());
    }

    public void setUserPanelController(UserPanelController userPanelController) {
        this.userPanelController = userPanelController;
    }

    @FXML
    private void onThemeChange() {
        // TODO - fix theme switching
        ThemeType currenTheme = themeController.getCurrentTheme();
        themeController.setTheme(
            currenTheme == ThemeType.DARK ? ThemeType.LIGHT : ThemeType.DARK
        );
    }

    private void openUserPanel() {
        userPanelController.openPanel();
    }
}
