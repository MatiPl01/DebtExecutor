package pl.age.edu;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import pl.age.edu.controllers.core.SceneController;
import pl.age.edu.controllers.core.SceneType;

@SpringBootApplication
@Component
public class App extends Application {
    private static ConfigurableApplicationContext springContext;

    public static ApplicationContext getContext() {
        return springContext;
    }

//    @Autowired
//    SceneController sceneController;

    @Override
    public void start(Stage primaryStage) {
        setupStage(primaryStage);

//        System.out.println(sceneController);

        // Set up the scene controller
        SceneController sceneController = SceneController.getInstance();
        sceneController.setStage(primaryStage);

        // Open the authentication scene
        sceneController.switchScene(SceneType.AUTH);

        // Show the primary stage
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(App.class);
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    public static void init(String[] args) {
        Application.launch(App.class, args);
    }

    private static void setupStage(Stage primaryStage) {
        primaryStage.setTitle("Debt Executor");
        primaryStage.setWidth(720);
        primaryStage.setHeight(480);
        primaryStage.setMinWidth(400);
    }
}
