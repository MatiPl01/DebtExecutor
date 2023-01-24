package pl.edu.agh.debtexecutor.graphs;

import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.graphs.model.BasicGraph;
import pl.edu.agh.debtexecutor.graphs.model.simplifiedGraph.SimplifiedGraph;
import pl.edu.agh.debtexecutor.users.model.User;

import java.util.List;

public class GraphFactory {
    public static BasicGraph createHistoryGraph(List<User> users, List<Expense> expenses) {
        BasicGraph graph = new BasicGraph();
        users.forEach(graph::addVertex);
        expenses.forEach(expense -> graph.addEdge(
                expense.getPayer(),
                expense.getPayee(),
                expense.getAmount()
        ));
        return graph;
    }

    public static BasicGraph createSummaryGraph(List<User> users) {
        BasicGraph graph = new BasicGraph();
        users.forEach(graph::addVertex);
        users.forEach(payer -> {
            payer.getBalance()
                 .entrySet()
                 .stream()
                 .filter(entry -> entry.getValue().signum() > 0)
                 .forEach(entry -> graph.addEdge(payer, entry.getKey(), entry.getValue()));
        });
        return graph;
    }

    public static SimplifiedGraph createSimplifiedGraph(List<User> users, List<Expense> expenses) {
        SimplifiedGraph graph = new SimplifiedGraph();
        users.forEach(graph::addUser);
        expenses.forEach(expense -> {
            graph.addExpense(expense);

            System.out.println("Expense: " + expense);
            System.out.println(graph.getVertices());
            System.out.println(graph.getEdges());
            System.out.println("---");
        });
        return graph;
    }
}
