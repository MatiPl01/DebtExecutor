package pl.edu.agh.debtexecutor.graphs.model.simplifiedGraph;

import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.graphs.model.Edge;
import pl.edu.agh.debtexecutor.graphs.model.Graph;
import pl.edu.agh.debtexecutor.graphs.model.Vertex;
import pl.edu.agh.debtexecutor.users.model.User;

import java.math.BigDecimal;
import java.util.*;

public class SimplifiedGraph implements Graph {
    private final Map<User, SimplifiedVertex> userVertexMap = new HashMap<>();

    @Override
    public List<Edge> getEdges() {
        Set<SimplifiedEdge> edges = new HashSet<>();
        userVertexMap.values().forEach(vertex -> edges.addAll(vertex.getOutEdges()));
        return edges.stream().map(SimplifiedEdge::toEdge).toList();
    }

    @Override
    public List<Vertex> getVertices() {
        return userVertexMap.values().stream().map(SimplifiedVertex::toVertex).toList();
    }

    public void addExpense(Expense expense) {
        SimplifiedVertex fromVertex = getVertex(expense.getPayer());
        SimplifiedVertex toVertex = getVertex(expense.getPayee());
        insertEdge(fromVertex, toVertex, expense.getAmount());
    }

    public void addUser(User user) {
        if (userVertexMap.containsKey(user)) return;
        userVertexMap.put(user, new SimplifiedVertex(user));
    }

    private SimplifiedVertex getVertex(User user) {
        if (userVertexMap.get(user) == null) {
            userVertexMap.put(user, new SimplifiedVertex(user));
        }
        return userVertexMap.get(user);
    }

    private void insertEdge(SimplifiedVertex fromVertex,
                            SimplifiedVertex toVertex,
                            BigDecimal value) {
        // If there is already an edge between vertices directed the same as the new edge
        Optional<SimplifiedEdge> matchingEdge = fromVertex.getEdgeTo(toVertex);
        if (matchingEdge.isPresent()) {
            insertEdgeMatching(matchingEdge.get(), value);
            return;
        }
        // If there is already an edge in the opposite direction
        Optional<SimplifiedEdge> oppositeEdge = toVertex.getEdgeTo(fromVertex);
        if (oppositeEdge.isPresent()) {
            insertEdgeOpposite(oppositeEdge.get(), value);
            return;
        }
        // Otherwise, if there is no edge, add a new one
        insertNewEdge(new SimplifiedEdge(fromVertex, toVertex, value));
    }

    private void insertEdgeMatching(SimplifiedEdge matchingEdge,
                                    BigDecimal updateValue) {
        matchingEdge.updateValue(updateValue);
    }

    private void insertEdgeOpposite(SimplifiedEdge oppositeEdge,
                                    BigDecimal updateValue) {
        int valueDiff = updateValue.compareTo(oppositeEdge.getValue());
        // Change edge direction if the new value is greater than the value of the opposite edge
        if (valueDiff > 0) {
            BigDecimal newValue = updateValue.subtract(oppositeEdge.getValue());
            oppositeEdge.remove();
            insertNewEdge(new SimplifiedEdge(
                    oppositeEdge.getToVertex(),
                    oppositeEdge.getFromVertex(),
                    newValue
            ));
        // Decrease the edge value id the new value is lower than the value of the opposite edge
        } else if (valueDiff < 0) {
            oppositeEdge.updateValue(updateValue.negate());
        // Remove the edge if values are the same
        } else {
            oppositeEdge.remove();
        }
    }

    private void insertNewEdge(SimplifiedEdge newEdge) {
        newEdge.getFromVertex().addEdge(newEdge);
        newEdge.getToVertex().addEdge(newEdge);
        simplifyPaths(newEdge.getFromVertex());
        simplifyPaths(newEdge.getToVertex());
    }

    private void simplifyPaths(SimplifiedVertex middleVertex) {
        for (SimplifiedEdge inEdge: middleVertex.getInEdges()) {
            for (SimplifiedEdge outEdge: middleVertex.getOutEdges()) {
                simplifyEdges(inEdge, outEdge);
            }
        }
    }

    private void simplifyEdges(SimplifiedEdge firstEdge, SimplifiedEdge secondEdge) {
        int valueDiff = firstEdge.getValue().compareTo(secondEdge.getValue());
        SimplifiedEdge newEdge;

        /* The first edge has greater value than the second edge (a > b)
         *                 a - b  |----|
         *                        |    v
         *   A -> B -> C    ->    A    B    C
         *     a    b             |    b    ^
         *                        |---------|
         */
        if (valueDiff > 0) {
            firstEdge.updateValue(secondEdge.getValue().negate()); // a - b
            secondEdge.remove(); // B -> C

            newEdge = new SimplifiedEdge( // A -> C
                firstEdge.getFromVertex(),
                secondEdge.getToVertex(),
                secondEdge.getValue() // b
            );
        /* The first edge has lower value than the second edge (a < b)
         *                      b - a  |----|
         *                             |    v
         *   A -> B -> C    ->    A    B    C
         *     a    b             |    a    ^
         *                        |---------|
         */
        } else if (valueDiff < 0) {
            secondEdge.updateValue(firstEdge.getValue().negate()); // b - a
            firstEdge.remove(); // A -> B

            newEdge = new SimplifiedEdge( // A -> C
                firstEdge.getFromVertex(),
                secondEdge.getToVertex(),
                firstEdge.getValue()
            );
        /* Both edges have the same values (a == b)
         *   A -> B -> C    ->    A    B    C
         *     a    b             |    a    ^
         *                        |---------|
         */
        } else {
            firstEdge.remove(); // A -> B
            secondEdge.remove(); // B -> C

            newEdge = new SimplifiedEdge( // A -> C
                firstEdge.getFromVertex(),
                secondEdge.getToVertex(),
                firstEdge.getValue()
            );
        }

        insertEdge(
            newEdge.getFromVertex(),
            newEdge.getToVertex(),
            newEdge.getValue()
        );
    }
}
