package pl.age.edu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InitialScreenController {
    @FXML
    private Button pressMeButton;

    @FXML
    public void onPressMeButtonClick() {
        System.out.println("I was pressed :)))");
    }

    @FXML
    private void initialize() {

    }
}
