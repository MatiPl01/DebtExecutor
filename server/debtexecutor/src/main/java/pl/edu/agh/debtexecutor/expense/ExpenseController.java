package pl.edu.agh.debtexecutor.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.debtexecutor.user.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseFactory expenseFactory;

    @Autowired
    public ExpenseController(ExpenseService expenseService, ExpenseFactory expenseFactory) {
        this.expenseService = expenseService;
        this.expenseFactory = expenseFactory;
    }

    @GetMapping
    public List<ExpenseDTO> getExpenses() {
        return expenseService.getExpenses().stream().map(ExpenseDTO::from).toList();
    }

    @PostMapping("/add/expense")
    public void addExpense(@RequestBody CreateExpenseDTO dto) throws IllegalStateException {
        Expense expense = expenseFactory.createExpense(dto);
        expenseService.addExpense(expense);
    }
    @PostMapping("/add/group-expense")
    public void addExpense(@RequestBody CreateGroupExpenseDTO dto) throws IllegalStateException {
        List<Expense> expenses = expenseFactory.createExpense(dto);
        expenseService.addExpenses(expenses);
    }
}