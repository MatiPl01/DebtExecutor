package pl.age.edu.controllers.core;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.Objects;

public class TopBarController {
    private boolean isThemeDark = false;

    @FXML
    private Button darkLightSwitch;

    @FXML
    private void initialize() {

    }

    @FXML
    private void onThemeChange() {
        Scene scene = darkLightSwitch.getScene();
        // TODO - cache stylesheets somewhere
        String lightCSS = Objects.requireNonNull(getClass().getResource("/css/themes/light.css")).toExternalForm();
        String darkCSS = Objects.requireNonNull(getClass().getResource("/css/themes/dark.css")).toExternalForm();

        System.out.println("click: " + isThemeDark);

        if (isThemeDark) {
            scene.getStylesheets().remove(darkCSS);
            scene.getStylesheets().add(lightCSS);
        } else {
            scene.getStylesheets().remove(lightCSS);
            scene.getStylesheets().add(darkCSS);
        }

        isThemeDark = !isThemeDark;
    }
}
