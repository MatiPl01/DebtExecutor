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

    public Optional<GetExpensesResponseDTO> getExpenses(int pageSize,
                                     int pageNumber,
                                     String sortBy,
                                     SortDirection sortDirection,
                                     Optional<List<String>> categories) {
        try {
            if (categories.isPresent()) {
                return Optional.ofNullable(expenseApiService.getExpenses(
                        pageSize,
                        pageNumber - 1,
                        sortBy,
                        sortDirection.toString(),
                        String.join(",", categories.get())
                ).execute().body());
            }

            return Optional.ofNullable(expenseApiService.getExpenses(
                    pageSize,
                    pageNumber - 1,
                    sortBy,
                    sortDirection.toString()
            ).execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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
