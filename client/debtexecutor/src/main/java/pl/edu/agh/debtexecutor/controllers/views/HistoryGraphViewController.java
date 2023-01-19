package pl.edu.agh.debtexecutor.controllers.views;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.services.ExpenseService;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HistoryGraphViewController extends HistoryViewAbstractController
        implements Initializable {

    @FXML private AnchorPane graphWrapper;

    @Autowired
    private ExpenseService expenseService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        expenseService.fetchAllData();

        Graph<String, String> g = new GraphEdgeList<>();
    }
}
