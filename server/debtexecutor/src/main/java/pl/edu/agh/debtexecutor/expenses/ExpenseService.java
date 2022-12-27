package pl.edu.agh.debtexecutor.expenses;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.expenses.model.ExpensePage;
import pl.edu.agh.debtexecutor.expenses.model.ExpenseSearchCriteria;
import pl.edu.agh.debtexecutor.expenses.repository.ExpenseCriteriaRepository;
import pl.edu.agh.debtexecutor.expenses.repository.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseCriteriaRepository expenseCriteriaRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          ExpenseCriteriaRepository expenseCriteriaRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseCriteriaRepository = expenseCriteriaRepository;
    }

    public Page<Expense> getExpenses(ExpensePage expensePage,
                                     ExpenseSearchCriteria expenseSearchCriteria) {
        return expenseCriteriaRepository.findAllWithFilters(
                expensePage,
                expenseSearchCriteria
        );
    }

    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public void addExpenses(List<Expense> expenses) {
        expenseRepository.saveAll(expenses);
    }
}
