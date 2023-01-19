package pl.edu.agh.debtexecutor.graphs.model.simplifiedGraph;

import pl.edu.agh.debtexecutor.users.model.User;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static pl.edu.agh.debtexecutor.graphs.model.simplifiedGraph.SimplifiedEdge.*;

record SimplifiedVertex(User user, Map<SimplifiedVertex, SimplifiedEdge> edges) {
    public void addEdge(SimplifiedVertex vertex, BigDecimal value) {
        if (value.equals(BigDecimal.ZERO)) {
            edges.remove(vertex);
        } else {
            SimplifiedEdge edge = new SimplifiedEdge(this, vertex, value);
            edges.put(vertex, edge);
        }
    }

    public void increaseEdge(SimplifiedVertex vertex, BigDecimal value) {
        if (edges.containsKey(vertex)) {
            SimplifiedEdge edge = new SimplifiedEdge(this, vertex, edges.get(vertex).value().add(value));
            edges.put(vertex, edge);
        } else {
            addEdge(vertex, value);
        }
    }

    public void decreaseEdge(SimplifiedVertex vertex, BigDecimal value) {
        addEdge(vertex, edges.get(vertex).value().subtract(value));
    }

    public BigDecimal getEdgeValue(SimplifiedVertex vertex) {
        return edges.get(vertex).value();
    }

    public void removeEdge(SimplifiedVertex vertex) {
        edges.remove(vertex);
    }

    public Optional<SimplifiedEdge> getLowestEdge() {
        return edges.values().stream().min(new EdgeComparator());
    }
}

