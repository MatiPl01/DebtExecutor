package pl.edu.agh.debtexecutor.graphs;

import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.expenses.ExpenseService;
import pl.edu.agh.debtexecutor.graphs.model.Graph;
import pl.edu.agh.debtexecutor.users.UserService;

@Service
public class GraphService {
    private final UserService userService;
    private final ExpenseService expenseService;
    private final SimplifiedGraphService simplifiedGraphService;

    public GraphService(UserService userService,
                        ExpenseService expenseService,
                        SimplifiedGraphService simplifiedGraphService) {
        this.userService = userService;
        this.expenseService = expenseService;
        this.simplifiedGraphService = simplifiedGraphService;
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
        return simplifiedGraphService.getGraph();
    }
}
