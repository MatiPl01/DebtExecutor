package pl.edu.agh.debtexecutor.graphs.model;

import java.util.List;

public interface Graph {
    public List<Edge> getEdges();

    public List<Vertex> getVertices();
}
