package pl.age.edu.controllers.core;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.age.edu.utils.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public class SceneController {
    private Stage stage;
    private Scene currentScene;
    private final Map<SceneType, Scene> scenes = new HashMap<>();
    private final ThemeController themeController = ThemeController.getInstance();

    private static final SceneController instance = new SceneController();

    private SceneController() {
        loadScenes();
    }

    public static SceneController getInstance() {
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void switchScene(SceneType newScene) {
        currentScene = scenes.get(newScene);
        stage.setScene(currentScene);
        // Load theme
        themeController.setScene(currentScene);
    }

    private void loadScenes() {
        String css = ResourceLoader.loadCSS("/css/shared.css");
//        loadScene(SceneType.AUTH, "/fxml/Auth.fxml", css);
        loadScene(SceneType.MAIN, "/fxml/Main.fxml", css);
    }

    private void loadScene(SceneType sceneType, String resourcePath, String css) {
        Scene scene = new Scene(ResourceLoader.loadFXML(resourcePath));
        scene.getStylesheets().add(css);
        scenes.put(sceneType, scene);
    }
}
