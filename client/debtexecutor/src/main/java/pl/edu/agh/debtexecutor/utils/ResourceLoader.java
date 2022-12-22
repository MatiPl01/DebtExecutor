package pl.edu.agh.debtexecutor.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import pl.edu.agh.debtexecutor.App;
import pl.edu.agh.debtexecutor.controllers.core.ThemeController;

import java.util.Objects;

public class ResourceLoader {
    public static <T extends Node> T loadFXML(String resourcePath) {
        FXMLLoader loader =
                new FXMLLoader(ResourceLoader.class.getResource(resourcePath));
        loader.setControllerFactory(App.getContext()::getBean);
        try {
            return loader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadCSS(String resourcePath) {
        return Objects.requireNonNull(ThemeController.class.getResource(
                resourcePath)).toExternalForm();
    }
}