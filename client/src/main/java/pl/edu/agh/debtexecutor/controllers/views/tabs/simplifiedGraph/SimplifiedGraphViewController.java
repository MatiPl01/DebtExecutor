package pl.edu.agh.debtexecutor.controllers.views.tabs.simplifiedGraph;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.api.graph.GraphApi;
import pl.edu.agh.debtexecutor.controls.GraphVisualization;
import pl.edu.agh.debtexecutor.models.graph.GraphModel;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class SimplifiedGraphViewController implements Initializable {
    @FXML private GraphVisualization graphVisualization;

    @Autowired private GraphApi graphApi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Optional<GraphModel> graph = graphApi.getSimplifiedExpenseGraph();
        graph.ifPresent(value -> graphVisualization.setGraph(value));
    }
}
