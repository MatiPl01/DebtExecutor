package pl.edu.agh.debtexecutor.graphs;

import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.graphs.model.simplifiedGraph.SimplifiedGraph;
import pl.edu.agh.debtexecutor.users.model.User;

import java.util.List;

@Service
public class SimplifiedGraphService {
    private final SimplifiedGraph simplifiedGraph = new SimplifiedGraph();

    public void addExpense(Expense expense) {
        simplifiedGraph.addExpense(expense);
    }

    public void addExpenses(List<Expense> expenses) {
        expenses.forEach(simplifiedGraph::addExpense);
    }

    public void addUser(User user) {
        simplifiedGraph.addUser(user);
    }

    public void addUsers(List<User> users) {
        users.forEach(simplifiedGraph::addUser);
    }

    public SimplifiedGraph getGraph() {
        return simplifiedGraph;
    }
}
