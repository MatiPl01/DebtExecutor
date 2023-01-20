package pl.edu.agh.debtexecutor.controls;

import com.brunomnsilva.smartgraph.containers.ContentZoomPane;
import com.brunomnsilva.smartgraph.graph.DigraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import pl.edu.agh.debtexecutor.models.graph.Edge;
import pl.edu.agh.debtexecutor.models.graph.GraphModel;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;
import pl.edu.agh.debtexecutor.utils.Timeout;

import java.util.List;

public class GraphVisualization extends AnchorPane {
    private static final String FXML_PATH =
            "/fxml/controls/GraphVisualization.fxml";

    @FXML
    private AnchorPane graphWrapper;

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

        graphWrapper.getChildren().setAll(new ContentZoomPane(graphView));
        graphView.setPrefWidth(380);
        graphView.setPrefHeight(360);
        graphView.setAutomaticLayout(true);

        new Timeout(graphView::init, 200);
    }

    private Graph<String, String> buildGraph() {
        Graph<String, String> graph = new DigraphEdgeList<>();

        graphModel.getVertices()
                  .forEach(vertex -> graph.insertVertex(vertex.toString()));

        List<Edge> edges = graphModel.getEdges();
        for (int i = 0; i < graphModel.getEdges().size(); i++) {
            Edge edge = edges.get(i);
            graph.insertEdge(
                    edge.getFromVertex().toString(),
                    edge.getToVertex().toString(),
                    String.format("%s (%d)", edge.getAmount(), i + 1)
            );
        }

        return graph;
    }
}
