package pl.edu.agh.debtexecutor.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Controller;

@Controller
public class SummaryGraphViewController extends SummaryViewAbstractController {
    @FXML
    private AnchorPane graphWrapper;
}
