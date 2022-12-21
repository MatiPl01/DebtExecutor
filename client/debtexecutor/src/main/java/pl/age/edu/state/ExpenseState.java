package pl.age.edu.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import pl.age.edu.api.expense.ExpenseApi;
import pl.age.edu.api.expense.dto.CreateExpenseDTO;
import pl.age.edu.api.expense.dto.CreateGroupExpenseDTO;
import pl.age.edu.models.Expense;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ExpenseState {
    private final ObservableList<Expense> storedExpenses = FXCollections.observableArrayList();

    private final ExpenseApi expenseApi;

    private ExpenseState(ExpenseApi expenseApi) {
        this.expenseApi = expenseApi;
    }

    public ObservableList<Expense> getExpenses() {
        return storedExpenses;
    }

    public void fetchData() {
        storedExpenses.setAll(expenseApi.getAll());
    }

    public Optional<Expense> addPersonalExpense(String title, BigDecimal amount, String payer, String payee) {
        CreateExpenseDTO dto = new CreateExpenseDTO(title, payer, payee, amount);
        Optional<Expense> expense = expenseApi.createPersonalExpense(dto);
        expense.ifPresent(storedExpenses::add);
        return expense;
    }

    public List<Expense> addGroupExpense(String title, BigDecimal amount, String payer, String group) {
        CreateGroupExpenseDTO dto = new CreateGroupExpenseDTO(title, payer, group, amount);
        List<Expense> expenses = expenseApi.createGroupExpense(dto);
        storedExpenses.addAll(expenses);
        return expenses;
    }
}
