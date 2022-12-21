package pl.edu.agh.debtexecutor.expenses;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.debtexecutor.expenses.dto.CreateExpenseDTO;
import pl.edu.agh.debtexecutor.expenses.dto.CreateGroupExpenseDTO;
import pl.edu.agh.debtexecutor.expenses.dto.ExpenseDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseFactory expenseFactory;

    public ExpenseController(ExpenseService expenseService,
                             ExpenseFactory expenseFactory) {
        this.expenseService = expenseService;
        this.expenseFactory = expenseFactory;
    }

    @GetMapping
    public List<ExpenseDTO> getExpenses() {
        return expenseService
                .getExpenses()
                .stream()
                .map(ExpenseDTO::from)
                .toList();
    }

    @PostMapping
    @Transactional
    public @ResponseBody ExpenseDTO addExpense(
            @RequestBody CreateExpenseDTO dto
    ) throws ResponseStatusException {
        try {
            Expense expense = expenseFactory.createExpense(dto);
            expenseService.addExpense(expense);
            changeBalances(expense);
            return ExpenseDTO.from(expense);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to add an expense:\n" + e.getMessage()
            );
        }
    }

    @PostMapping("/group")
    @Transactional
    public @ResponseBody List<ExpenseDTO> addExpense(
            @RequestBody CreateGroupExpenseDTO dto
    ) throws ResponseStatusException {
        try {
            List<Expense> expenses = expenseFactory.createExpense(dto);
            expenseService.addExpenses(expenses);
            expenses.forEach(expense -> {
                changeBalances(expense);
                expense.getGroup().get().addExpense(expense);
            });

            return expenses.stream().map(ExpenseDTO::from).toList();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to add a group expense:\n" + e.getMessage()
            );
        }
    }

    private void changeBalances(Expense expense) {
        expense.getPayer().changeBalance(expense.getPayee(), expense.getAmount());
        expense.getPayee().changeBalance(expense.getPayer(), expense.getAmount().negate());
    }
}
