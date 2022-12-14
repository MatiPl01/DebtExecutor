package pl.age.edu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        setupStage(primaryStage);

        // Load the initial scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/auth/AuthView.fxml"));
        AnchorPane borderPane = loader.load();
        Scene scene = new Scene(borderPane);

        // Load CSS stylesheet and add to the scene
        scene.getStylesheets().addAll(
//            Objects.requireNonNull(getClass().getResource("/css/themes/dark.css")).toExternalForm(),
            Objects.requireNonNull(getClass().getResource("/css/themes/light.css")).toExternalForm(),
            Objects.requireNonNull(getClass().getResource("/css/shared.css")).toExternalForm()
        );

        primaryStage.setScene(scene);
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
