package pl.edu.agh.debtexecutor.controls;

import com.brunomnsilva.smartgraph.containers.ContentZoomPane;
import com.brunomnsilva.smartgraph.graph.DigraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pl.edu.agh.debtexecutor.models.graph.Edge;
import pl.edu.agh.debtexecutor.models.graph.GraphModel;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;
import pl.edu.agh.debtexecutor.utils.Timeout;

import java.util.List;

public class GraphVisualization extends VBox {
    private static final String FXML_PATH =
            "/fxml/controls/GraphVisualization.fxml";

    @FXML private VBox graphWrapper;

    private GraphModel graphModel;

    public GraphVisualization(GraphModel graph) {
        loadFXML();
        setGraph(graph);
    }

    public GraphVisualization() {
        loadFXML();
    }

    public void setGraph(GraphModel graph) {
        graphModel = graph;
        renderGraph();
    }

    private void loadFXML() {
        ResourceLoader.loadControlFXML(FXML_PATH, this);
    }

    private void renderGraph() {
        Graph<String, String> graph = buildGraph();

        SmartPlacementStrategy strategy =
                new SmartCircularSortedPlacementStrategy();
        SmartGraphPanel<String, String> graphView =
                new SmartGraphPanel<>(graph, strategy);

        ContentZoomPane contentZoomPane = new ContentZoomPane(graphView);
        contentZoomPane.getStyleClass().add("content-zoom-pane");
        graphWrapper.getChildren().setAll(contentZoomPane);
        AnchorPane.setLeftAnchor(graphWrapper, 0.0);
        AnchorPane.setLeftAnchor(graphWrapper, 0.0);
        AnchorPane.setRightAnchor(graphWrapper, 0.0);
        AnchorPane.setTopAnchor(graphWrapper, 0.0);
        graphView.setAutomaticLayout(true);

        new Timeout(graphView::init, 0);
    }

    private Graph<String, String> buildGraph() {
        Graph<String, String> graph = new DigraphEdgeList<>();

        graphModel.getVertices()
                  .forEach(vertex -> graph.insertVertex(vertex.toString()));

        List<Edge> edges = graphModel.getEdges();
        for (int i = 0; i < graphModel.getEdges().size(); i++) {
            Edge edge = edges.get(i);
            graph.insertEdge(
                    edge.getToVertex().toString(),
                    edge.getFromVertex().toString(),
                    String.format("%s (%d)", edge.getAmount(), i + 1)
            );
        }

        return graph;
    }
}
