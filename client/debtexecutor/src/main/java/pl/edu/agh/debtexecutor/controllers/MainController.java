package pl.edu.agh.debtexecutor.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controllers.core.SceneController;
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
    @FXML private HBox headerContent;
    @FXML private SidePanelController sidePanelController;
    @FXML private TopBarController topBarController;
    @FXML private UserPanelController userPanelController;
    @FXML private ViewController viewController;

    @Autowired private SceneController sceneController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpControllers();
    }

    private void setUpControllers() {
        viewController = new ViewController(viewWrapper, viewLabel, headerContent);
        topBarController.setUserPanelController(userPanelController);
        sidePanelController.setViewController(viewController);
    }
}
