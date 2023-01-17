package pl.edu.agh.debtexecutor.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.expense.ExpenseApi;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateGroupExpenseDTO;
import pl.edu.agh.debtexecutor.models.Expense;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ExpenseService {
    private final ObservableList<Expense> storedExpenses = FXCollections.observableArrayList();

    private final ExpenseApi expenseApi;

    private ExpenseService(ExpenseApi expenseApi) {
        this.expenseApi = expenseApi;
    }

    public ObservableList<Expense> getExpenses() {
        return storedExpenses;
    }

    public void fetchData() {
        // TODO - fix this (add pagination)
//        storedExpenses.setAll(expenseApi.getAll());
    }

    public Optional<Expense> addPersonalExpense(String title, BigDecimal amount, UUID payerId, UUID payeeId, UUID categoryId) {
        CreateExpenseDTO dto = new CreateExpenseDTO(title, payerId, payeeId, categoryId, amount);
        Optional<Expense> expense = expenseApi.createPersonalExpense(dto);
        expense.ifPresent(storedExpenses::add);
        return expense;
    }

    public List<Expense> addGroupExpense(String title, BigDecimal amount, UUID payerId, UUID groupId, UUID categoryId) {
        CreateGroupExpenseDTO dto = new CreateGroupExpenseDTO(title, payerId, groupId, categoryId, amount);
        List<Expense> expenses = expenseApi.createGroupExpense(dto);
        storedExpenses.addAll(expenses);
        return expenses;
    }
}
