package pl.edu.agh.debtexecutor.graphs.dto;

import pl.edu.agh.debtexecutor.graphs.model.Vertex;

public record VertexDTO(String userName, String login) {
    public static VertexDTO from(Vertex vertex) {
        return new VertexDTO(vertex.getUserName(), vertex.getUserLogin());
    }
}
