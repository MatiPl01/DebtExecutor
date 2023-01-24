package pl.edu.agh.debtexecutor.models.graph;

import java.util.List;

public final class GraphModel {
    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public GraphModel(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
