package pl.edu.agh.debtexecutor.graphs.model;

import java.util.List;

public interface Graph {
    List<Edge> getEdges();

    List<Vertex> getVertices();
}
