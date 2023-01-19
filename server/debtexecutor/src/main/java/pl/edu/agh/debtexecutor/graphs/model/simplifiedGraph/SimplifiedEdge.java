package pl.edu.agh.debtexecutor.graphs.model.simplifiedGraph;

import pl.edu.agh.debtexecutor.graphs.model.Edge;
import pl.edu.agh.debtexecutor.graphs.model.Vertex;

import java.math.BigDecimal;
import java.util.Comparator;

record SimplifiedEdge(SimplifiedVertex vFrom, SimplifiedVertex vTo, BigDecimal value) {

    public Edge toEdge() {
        return new Edge(new Vertex(vFrom.user()), new Vertex(vTo.user()), value);
    }

    public static class EdgeComparator implements Comparator<SimplifiedEdge> {
        @Override
        public int compare(SimplifiedEdge o1, SimplifiedEdge o2) {
            return o1.value.compareTo(o2.value());
        }
    }
}


