package pl.edu.agh.debtexecutor.controllers.core;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.services.GroupService;
import pl.edu.agh.debtexecutor.services.UserService;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SceneController {
    private Stage stage;
    private final Map<SceneType, Scene> scenes = new HashMap<>();
    private final String SHARED_CSS = ResourceLoader.loadCSS(
            "/css/shared.css"
    );

    private final ThemeController themeController;
    private final UserService userService;
    private final GroupService groupService;

    private SceneController(ThemeController themeController,
                            UserService userService,
                            GroupService groupService) {
        this.themeController = themeController;
        this.userService = userService;
        this.groupService = groupService;
    }

    public void setStage(Stage stage) {
        loadScenes();
        this.stage = stage;
    }

    public void switchScene(SceneType newScene) {
        Scene scene = scenes.get(newScene);
        stage.setScene(scene);
        // Load theme
        themeController.setScene(scene);

        // Fetch users and groups on main scene render
        if (newScene == SceneType.MAIN) {
            userService.fetchData();
            groupService.fetchData();
        }
    }

    private void loadScenes() {
        loadScene(SceneType.AUTH, "/fxml/Auth.fxml", SHARED_CSS);
        loadScene(SceneType.MAIN, "/fxml/Main.fxml", SHARED_CSS);
    }

    private void loadScene(SceneType sceneType,
                           String resourcePath,
                           String css) {
        Scene scene = new Scene(ResourceLoader.loadFXML(resourcePath));
        scene.getStylesheets().add(css);
        scenes.put(sceneType, scene);
    }
}
