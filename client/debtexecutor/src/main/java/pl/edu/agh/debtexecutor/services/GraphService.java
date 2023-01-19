package pl.edu.agh.debtexecutor.services;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.graph.GraphApi;
import pl.edu.agh.debtexecutor.api.graph.dto.GraphDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class GraphService {
    private final List<GraphDTO> graph = new ArrayList<>();
    private final GraphApi graphApi;

    private GraphService(GraphApi graphApi) {
        this.graphApi = graphApi;
    }

    private void getGraphData() {
        // Fetching graph edges and vertices from graphApi.
        this.graph.add(new GraphDTO(graphApi.getGraphVertices(), graphApi.getGraphEdges()));
    }

    public List<GraphDTO> getGraph() {
        getGraphData();
        return graph;
    }
}
