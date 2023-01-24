package pl.edu.agh.debtexecutor.graphs.model.simplifiedGraph;

import pl.edu.agh.debtexecutor.graphs.model.Edge;

import java.math.BigDecimal;
import java.util.Objects;

public class SimplifiedEdge {
    private final SimplifiedVertex fromVertex;
    private final SimplifiedVertex toVertex;
    private BigDecimal value;

    public SimplifiedEdge(SimplifiedVertex fromVertex,
                          SimplifiedVertex toVertex,
                          BigDecimal value) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplifiedEdge edge = (SimplifiedEdge) o;
        return Objects.equals(fromVertex, edge.fromVertex) &&
               Objects.equals(toVertex, edge.toVertex) &&
               Objects.equals(value, edge.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromVertex, toVertex, value);
    }

    public Edge toEdge() {
        return new Edge(fromVertex.toVertex(), toVertex.toVertex(), value);
    }

    public SimplifiedVertex getFromVertex() {
        return fromVertex;
    }

    public SimplifiedVertex getToVertex() {
        return toVertex;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void updateValue(BigDecimal updateBy) {
        value = value.add(updateBy);
    }

    public void remove() {
        fromVertex.removeEdge(this);
        toVertex.removeEdge(this);
    }
}
