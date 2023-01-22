package pl.edu.agh.debtexecutor.controllers.core;

import javafx.scene.Scene;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ThemeController {
    private static final Map<ThemeType, String> themes = new HashMap<>() {{
        put(ThemeType.LIGHT, ResourceLoader.loadCSS("/css/themes/light.css"));
        put(ThemeType.DARK, ResourceLoader.loadCSS("/css/themes/dark.css"));
    }};

    private static ThemeType currentTheme = ThemeType.LIGHT;
    private static Scene scene;

    public void setScene(Scene scene) {
        ThemeController.scene = scene;
        setTheme(currentTheme);
    }

    public ThemeType getCurrentTheme() {
        return currentTheme;
    }

    public void setTheme(ThemeType themeType) {
        scene.getStylesheets().remove(themes.get(currentTheme));
        scene.getStylesheets().add(themes.get(themeType));
        currentTheme = themeType;
    }
}
