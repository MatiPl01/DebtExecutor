package pl.age.edu.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import pl.age.edu.api.user.UserController;
import pl.age.edu.controls.SummaryItem;
import pl.age.edu.models.User;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SummaryViewController implements Initializable {
    @FXML
    private VBox summariesWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<User> users = UserController.getAll();

        users.forEach(user -> summariesWrapper.getChildren().add(new SummaryItem(user)));
    }
}
