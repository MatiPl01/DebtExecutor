package pl.edu.agh.debtexecutor.api.expense;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateGroupExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.GetExpensesResponseDTO;
import pl.edu.agh.debtexecutor.models.Expense;
import pl.edu.agh.debtexecutor.services.options.SortDirection;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ExpenseApi {
    private final ExpenseApiService expenseApiService;

    public ExpenseApi(RetrofitClient retrofitClient) {
        expenseApiService =
                retrofitClient.getClient().create(ExpenseApiService.class);
    }

    public List<Expense> getExpenses(int pageSize,
                                     int pageNumber,
                                     String sortBy,
                                     SortDirection sortDirection,
                                     List<String> categories) {
        try {
            GetExpensesResponseDTO response;

            if (categories.isEmpty()) {
                response = expenseApiService.getExpenses(
                        pageSize,
                        pageNumber,
                        sortBy,
                        sortDirection.toString()
                ).execute().body();
            } else {
                response = expenseApiService.getExpenses(
                        pageSize,
                        pageNumber,
                        sortBy,
                        sortDirection.toString(),
                        String.join(",", categories)
                ).execute().body();
            }

            if (response != null) return response.content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<Expense> createPersonalExpense(CreateExpenseDTO dto) {
        try {
            return Optional.ofNullable(expenseApiService.createPersonalExpense(
                    dto).execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Expense> createGroupExpense(CreateGroupExpenseDTO dto) {
        try {
            List<Expense> expenses =
                    expenseApiService.createGroupExpense(dto).execute().body();
            if (expenses != null) return expenses;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
