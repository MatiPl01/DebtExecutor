package pl.edu.agh.debtexecutor.graphs.dto;

import pl.edu.agh.debtexecutor.graphs.model.Vertex;

public record VertexDTO(String label) {
    public static VertexDTO from(Vertex vertex) {
        return new VertexDTO(vertex.getLabel());
    }
}
