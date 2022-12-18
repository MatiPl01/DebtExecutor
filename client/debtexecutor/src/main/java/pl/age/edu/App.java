package pl.age.edu;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.age.edu.controllers.core.SceneController;
import pl.age.edu.controllers.core.SceneType;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        setupStage(primaryStage);

        // Set up the scene controller
        SceneController sceneController = SceneController.getInstance();
        sceneController.setStage(primaryStage);

        // Open the authentication scene
        sceneController.switchScene(SceneType.AUTH);

        // Show the primary stage
        primaryStage.show();
    }

    private static void setupStage(Stage primaryStage) {
        primaryStage.setTitle("Debt Executor");
        primaryStage.setWidth(720);
        primaryStage.setHeight(480);
        primaryStage.setMinWidth(400);
    }

    public static void init(String[] args) {
        Application.launch(App.class, args);
    }
}
