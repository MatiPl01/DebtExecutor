package pl.age.edu.utils;

import javafx.scene.Scene;

import java.util.HashMap;
import java.util.Map;

public class ThemeController {
    private static Scene scene;
    private static ThemeType currentTheme = ThemeType.LIGHT;
    private static final Map<ThemeType, String> themes = new HashMap<>();

    private static final ThemeController instance = new ThemeController();

    private ThemeController() {
        loadThemes();
    }

    public static ThemeController getInstance() {
        return instance;
    }

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

    private void loadThemes() {
        loadTheme(ThemeType.LIGHT, "/css/themes/light.css");
        loadTheme(ThemeType.DARK, "/css/themes/dark.css");
    }

    private void loadTheme(ThemeType themeType, String resourcePath) {
        themes.put(themeType, ResourceLoader.loadCSS(resourcePath));
    }
}
