package pl.edu.agh.debtexecutor.graphs.model;

import java.math.BigDecimal;

public record Edge(
        Vertex from,
        Vertex to,
        BigDecimal amount
) {
    @Override
    public String toString() {
        return from + " --" + amount.toString() + "-> " + to;
    }
}
