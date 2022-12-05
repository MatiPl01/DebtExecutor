package pl.age.edu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        setupStage(primaryStage);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/InitialScreen.fxml"));
        BorderPane borderPane = loader.load();

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void setupStage(Stage primaryStage) {
        primaryStage.setTitle("Debt Executor");
        primaryStage.setWidth(720);
        primaryStage.setHeight(480);
    }

    public static void init(String[] args) {
        Application.launch(App.class, args);
    }
}
