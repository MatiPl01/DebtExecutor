package pl.age.edu.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.controls.SummaryItem;
import pl.age.edu.models.User;
import pl.age.edu.state.UserState;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class SummaryViewController implements Initializable {
    @FXML
    private VBox summariesWrapper;

    @Autowired
    private UserState userState;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userState.fetchData();
        List<User> users = userState.getUsers();
        summariesWrapper.getChildren().setAll(users.stream().map(SummaryItem::new).toList());
    }
}
