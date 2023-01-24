package pl.edu.agh.debtexecutor.graphs.dto;

import pl.edu.agh.debtexecutor.graphs.model.Edge;

public record EdgeDTO(VertexDTO fromVertex, VertexDTO toVertex, String label) {
    public static EdgeDTO from(Edge edge) {
        return new EdgeDTO(
            VertexDTO.from(edge.from()),
            VertexDTO.from(edge.to()),
            edge.amount().toString()
        );
    }
}
