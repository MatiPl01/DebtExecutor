package pl.edu.agh.debtexecutor.controls;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import pl.edu.agh.debtexecutor.api.graph.dto.GraphDTO;
import pl.edu.agh.debtexecutor.api.graph.dto.GraphEdgeDTO;
import pl.edu.agh.debtexecutor.api.graph.dto.GraphVertexDTO;

import java.util.List;

public class GraphVisualization {
    // Create graph to use in HistoryViewController and SummaryViewController.
    private final List<GraphDTO> graph;

    public GraphVisualization(List<GraphDTO> graph) {
        this.graph = graph;
    }

    public SmartGraphPanel<String, String> createGraph() {
        // Convert graph to SmartGraphPanel.
        Graph<String, String> g = new GraphEdgeList<>();
        for (GraphDTO graphDTO : graph) {
            for (GraphVertexDTO vertex : graphDTO.vertices()) {
                g.insertVertex(vertex.vertex());
            }
            for (GraphEdgeDTO edge : graphDTO.edges()) {
                g.insertEdge(edge.vertex1(), edge.vertex2(), edge.label());
            }
        }
        SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();

        return new SmartGraphPanel<>(g, strategy);
    }
}
