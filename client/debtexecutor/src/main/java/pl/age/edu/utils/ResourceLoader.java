package pl.age.edu.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import pl.age.edu.controllers.core.ThemeController;

import java.io.IOException;
import java.util.Objects;

public class ResourceLoader {
    public static <T extends Node> T loadFXML(String resourcePath) {
        FXMLLoader loader = new FXMLLoader(ResourceLoader.class.getResource(resourcePath));
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadCSS(String resourcePath) {
        return Objects.requireNonNull(ThemeController.class.getResource(resourcePath)).toExternalForm();
    }
}