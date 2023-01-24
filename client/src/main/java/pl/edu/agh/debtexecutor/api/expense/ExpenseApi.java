package pl.edu.agh.debtexecutor.api.expense;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.ApiResponseHandler;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateGroupExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.GetExpensesResponseDTO;
import pl.edu.agh.debtexecutor.models.Expense;
import pl.edu.agh.debtexecutor.services.options.SortDirection;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ExpenseApi {
    private final ExpenseApiService expenseApiService;
    private final ApiResponseHandler handler;

    public ExpenseApi(RetrofitClient retrofitClient,
                      ApiResponseHandler handler) {
        this.handler = handler;
        expenseApiService =
                retrofitClient.getClient().create(ExpenseApiService.class);
    }

    public Optional<GetExpensesResponseDTO> getExpenses(int pageSize,
                                                        int pageNumber,
                                                        String sortBy,
                                                        SortDirection sortDirection,
                                                        Optional<List<String>> categories) {
        if (categories.isPresent()) {
            return handler.handleResponse(expenseApiService.getExpenses(
                    pageSize,
                    pageNumber - 1,
                    sortBy,
                    sortDirection.toString(),
                    String.join(",", categories.get())
            ));
        }

        return handler.handleResponse(expenseApiService.getExpenses(
                pageSize,
                pageNumber - 1,
                sortBy,
                sortDirection.toString()
        ));
    }

    public Optional<List<Expense>> getAllExpenses() {
        return handler.handleResponse(expenseApiService.getAllExpenses());
    }

    public Optional<Expense> createPersonalExpense(CreateExpenseDTO dto) {
        return handler.handleResponse(expenseApiService.createPersonalExpense(
                dto));
    }

    public Optional<List<Expense>> createGroupExpense(CreateGroupExpenseDTO dto) {
        return handler.handleResponse(expenseApiService.createGroupExpense(dto));
    }
}
