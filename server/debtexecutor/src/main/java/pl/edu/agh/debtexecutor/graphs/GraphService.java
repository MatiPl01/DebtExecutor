package pl.edu.agh.debtexecutor.graphs;

import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.expenses.ExpenseService;
import pl.edu.agh.debtexecutor.graphs.model.Graph;
import pl.edu.agh.debtexecutor.users.UserService;

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
        return GraphFactory.createHistoryGraph(
            userService.getUsers(),
            expenseService.getAllExpenses()
        );
    }

    public Graph getSummaryGraph() {
        return GraphFactory.createSummaryGraph(
            userService.getUsers()
        );
    }

    public Graph getSimplifiedGraph() {
        return GraphFactory.createSimplifiedGraph(
            userService.getUsers(),
            expenseService.getAllExpenses()
        );
    }
}
