package pl.edu.agh.debtexecutor.expenses;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.expenses.model.ExpensePage;
import pl.edu.agh.debtexecutor.expenses.model.ExpenseSearchCriteria;
import pl.edu.agh.debtexecutor.expenses.repository.ExpenseCriteriaRepository;
import pl.edu.agh.debtexecutor.expenses.repository.ExpenseRepository;
import pl.edu.agh.debtexecutor.graphs.SimplifiedGraphService;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseCriteriaRepository expenseCriteriaRepository;
    private final SimplifiedGraphService simplifiedGraphService;

    public ExpenseService(ExpenseRepository expenseRepository,
                          ExpenseCriteriaRepository expenseCriteriaRepository,
                          SimplifiedGraphService simplifiedGraphService) {
        this.expenseRepository = expenseRepository;
        this.expenseCriteriaRepository = expenseCriteriaRepository;
        this.simplifiedGraphService = simplifiedGraphService;
        // Add existing expenses to the simplified expense graph
        simplifiedGraphService.addExpenses(expenseRepository.findAll());
    }

    public Page<Expense> getExpenses(ExpensePage expensePage,
                                     ExpenseSearchCriteria expenseSearchCriteria) {
        return expenseCriteriaRepository.findAllWithFilters(
                expensePage,
                expenseSearchCriteria
        );
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
        simplifiedGraphService.addExpense(expense);
    }

    public void addExpenses(List<Expense> expenses) {
        expenseRepository.saveAll(expenses);
        simplifiedGraphService.addExpenses(expenses);
    }
}
