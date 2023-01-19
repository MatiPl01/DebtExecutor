package pl.edu.agh.debtexecutor.api.graph;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.api.graph.dto.GraphEdgeDTO;
import pl.edu.agh.debtexecutor.api.graph.dto.GraphVertexDTO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class GraphApi {
    private final GraphApiService graphApiService;

    public GraphApi(RetrofitClient retrofitClient) {
        graphApiService = retrofitClient.getClient().create(GraphApiService.class);
    }

    public List<GraphVertexDTO> getGraphVertices() {
        try {
            List<GraphVertexDTO> vertices = graphApiService.getGraphVertices().execute().body();
            if (vertices != null) return vertices;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<GraphEdgeDTO> getGraphEdges() {
        try {
            List<GraphEdgeDTO> edges = graphApiService.getGraphEdges().execute().body();
            if (edges != null) return edges;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

