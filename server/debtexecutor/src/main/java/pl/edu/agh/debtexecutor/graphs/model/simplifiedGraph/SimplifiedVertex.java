package pl.edu.agh.debtexecutor.graphs.model.simplifiedGraph;

import pl.edu.agh.debtexecutor.graphs.model.Vertex;
import pl.edu.agh.debtexecutor.users.model.User;

import java.util.*;

public class SimplifiedVertex {
    // Edges from vertices that are keys
    private final Map<SimplifiedVertex, SimplifiedEdge> inEdges = new HashMap<>();
    // Edges to vertices that are keys
    private final Map<SimplifiedVertex, SimplifiedEdge> outEdges = new HashMap<>();

    private final User user;

    public SimplifiedVertex(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplifiedVertex that = (SimplifiedVertex) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    public Vertex toVertex() {
        return new Vertex(user);
    }

    public void addEdge(SimplifiedEdge edge) {
        if (edge.getFromVertex() == this) {
            outEdges.put(edge.getToVertex(), edge);
        } else if (edge.getToVertex() == this) {
            inEdges.put(edge.getFromVertex(), edge);
        } else {
            throw new IllegalArgumentException(
                "Edge is not connected with the current vertex"
            );
        }
    }

    public void removeEdge(SimplifiedEdge edge) {
        if (edge.getFromVertex() == this) {
            outEdges.remove(edge.getToVertex());
        } else if (edge.getToVertex() == this) {
            inEdges.remove(edge.getFromVertex());
        } else {
            throw new IllegalArgumentException(
                "Edge is not connected with the current vertex"
            );
        }
    }

    public Optional<SimplifiedEdge> getEdgeTo(SimplifiedVertex vertex) {
        return Optional.ofNullable(outEdges.get(vertex));
    }

    public Optional<SimplifiedEdge> getEdgeFrom(SimplifiedVertex vertex) {
        return Optional.ofNullable(inEdges.get(vertex));
    }

    public List<SimplifiedEdge> getInEdges() {
        return inEdges.values().stream().toList();
    }

    public List<SimplifiedEdge> getOutEdges() {
        return outEdges.values().stream().toList();
    }
}
