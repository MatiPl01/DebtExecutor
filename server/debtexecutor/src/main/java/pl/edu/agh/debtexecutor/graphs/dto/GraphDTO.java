package pl.edu.agh.debtexecutor.graphs.dto;

import pl.edu.agh.debtexecutor.graphs.model.Graph;

import java.util.List;

public record GraphDTO(
        List<VertexDTO> vertices,
        List<EdgeDTO> edges
) {
    public static GraphDTO from(Graph graph) {
        return new GraphDTO(
            graph.getVertices().stream().map(VertexDTO::from).toList(),
            graph.getEdges().stream().map(EdgeDTO::from).toList()
        );
    }
}
