package pl.edu.agh.debtexecutor.graphs;

import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.expenses.ExpenseService;
import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.graphs.model.BasicGraph;
import pl.edu.agh.debtexecutor.graphs.model.Graph;
import pl.edu.agh.debtexecutor.graphs.model.simplifiedGraph.SimplifiedGraph;
import pl.edu.agh.debtexecutor.users.UserService;
import pl.edu.agh.debtexecutor.users.model.User;

import java.util.List;

@Service
public class GraphService {
    private final UserService userService;
    private final ExpenseService expenseService;

    public GraphService(ExpenseService expenseService,
                        UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    public Graph getHistoryGraph() {
        BasicGraph graph = new BasicGraph();
        userService.getUsers().forEach(graph::addVertex);
        expenseService.getAllExpenses().forEach(expense ->
            graph.addEdge(
                expense.getPayer(),
                expense.getPayee(),
                expense.getAmount()
            ));
        return graph;
    }

    public Graph getSummaryGraph() {
        BasicGraph graph = new BasicGraph();
        List<User> users = userService.getUsers();
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

    public Graph getSimplifiedGraph() {
        SimplifiedGraph graph = new SimplifiedGraph();
        List<User> users = userService.getUsers();
        users.forEach(graph::addUser);
        List<Expense> expenses = expenseService.getAllExpenses();
        expenses.forEach(graph::addExpense);
        return graph;
    }
}
