package pl.age.edu.api.expense;

import org.springframework.stereotype.Component;
import pl.age.edu.api.RetrofitClient;
import pl.age.edu.api.expense.dto.CreateExpenseDTO;
import pl.age.edu.api.expense.dto.CreateGroupExpenseDTO;
import pl.age.edu.models.Expense;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ExpenseApi {
    private final  ExpenseApiService expenseService;
    public ExpenseApi() {
        expenseService = RetrofitClient.getRetrofitClient().create(ExpenseApiService.class);
    }

    public List<Expense> getAll() {
        Response<List<Expense>> response = null;
        try {
            response = expenseService.getExpenses().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response == null) {
            return Collections.emptyList();
        } else {
            return response.body();
        }
    }

    public Optional<Expense> createPersonalExpense(CreateExpenseDTO dto) {
        Optional<Response<Expense>> response = Optional.empty();
        try {
            response = Optional.of(expenseService.createPersonalExpense(dto).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body);
    }

    public List<Expense> createGroupExpense(CreateGroupExpenseDTO dto) {
        Response<List<Expense>> response = null;
        try {
            response = expenseService.createGroupExpense(dto).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response == null) {
            return Collections.emptyList();
        } else {
            return response.body();
        }
    }
}
