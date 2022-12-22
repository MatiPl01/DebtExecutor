package pl.edu.agh.debtexecutor.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controllers.core.ViewController;
import pl.edu.agh.debtexecutor.controllers.layout.SidePanelController;
import pl.edu.agh.debtexecutor.controllers.layout.TopBarController;
import pl.edu.agh.debtexecutor.controllers.layout.UserPanelController;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable {
    @FXML private AnchorPane viewWrapper;
    @FXML private Label viewLabel;
    @FXML private SidePanelController sidePanelController;
    @FXML private TopBarController topBarController;
    @FXML private UserPanelController userPanelController;
    @FXML private ViewController viewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpControllers();
    }

    private void setUpControllers() {
        viewController = new ViewController(viewWrapper, viewLabel);
        topBarController.setUserPanelController(userPanelController);
        sidePanelController.setViewController(viewController);
    }
}