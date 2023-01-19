package pl.edu.agh.debtexecutor.controllers.views;

import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controls.GraphVisualization;
import pl.edu.agh.debtexecutor.services.GraphService;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class SummaryGraphViewController extends SummaryViewAbstractController
        implements Initializable {

    @FXML private AnchorPane graphWrapper;

    @Autowired private GraphService graphService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SmartGraphPanel<String, String>
                graphVisualization = new GraphVisualization(graphService.getGraph()).createGraph();
    }
}
