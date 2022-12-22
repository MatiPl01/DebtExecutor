package pl.edu.agh.debtexecutor.api.expense;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateGroupExpenseDTO;
import pl.edu.agh.debtexecutor.models.Expense;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ExpenseApi {
    private final  ExpenseApiService expenseService;
    public ExpenseApi(RetrofitClient retrofitClient) {
        expenseService = retrofitClient.getClient().create(ExpenseApiService.class);
    }

    public List<Expense> getAll() {
        try {
            List<Expense> expenses = expenseService.getExpenses().execute().body();
            if (expenses != null) return expenses;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<Expense> createPersonalExpense(CreateExpenseDTO dto) {
        try {
            return Optional.ofNullable(expenseService.createPersonalExpense(dto).execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Expense> createGroupExpense(CreateGroupExpenseDTO dto) {
        try {
            List<Expense> expenses = expenseService.createGroupExpense(dto).execute().body();
            if (expenses != null) return expenses;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}