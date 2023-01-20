package pl.edu.agh.debtexecutor.models.graph;

public final class Edge {
    private final Vertex fromVertex;
    private final Vertex toVertex;
    private final String label;

    public Edge(Vertex fromVertex, Vertex toVertex, String label) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.label = label;
    }

    @Override
    public String toString() {
        // TODO
        return label;
    }

    public Vertex getFromVertex() {
        return fromVertex;
    }

    public Vertex getToVertex() {
        return toVertex;
    }

    public String getAmount() {
        return label;
    }
}
