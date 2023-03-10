package pl.edu.agh.debtexecutor.controllers.views.tabs.expenseSummary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controls.SummaryItem;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.UserService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class SummaryViewController extends SummaryViewAbstractController
        implements Initializable {
    @FXML private VBox summariesWrapper;

    @Autowired private UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService.fetchData();
        List<User> users = userService.getUsers();
        summariesWrapper.getChildren().setAll(users.stream().map(SummaryItem::new).toList());
    }
}
