package pl.edu.agh.debtexecutor.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import pl.edu.agh.debtexecutor.App;
import pl.edu.agh.debtexecutor.controllers.core.ThemeController;

import java.io.IOException;
import java.util.Objects;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

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

    public static <T extends Node, C> FXMLWithController<T, C> loadFXMLWithController(String resourcePath) {
        FXMLLoader loader =
                new FXMLLoader(ResourceLoader.class.getResource(resourcePath));
        loader.setControllerFactory(App.getContext()::getBean);
        try {
            T node = loader.load();
            C controller = loader.getController();
            return new FXMLWithController<>(node, controller);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Node> void loadControlFXML(String resourcePath, T controlClass) {
        FXMLLoader fxmlLoader = new FXMLLoader(getResource(resourcePath));
        fxmlLoader.setRoot(controlClass);
        fxmlLoader.setController(controlClass);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadCSS(String resourcePath) {
        return Objects.requireNonNull(ThemeController.class.getResource(
                resourcePath)).toExternalForm();
    }
}
