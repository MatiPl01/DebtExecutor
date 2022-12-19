package pl.age.edu.api.expense;

import pl.age.edu.api.RetrofitClient;
import pl.age.edu.api.expense.dto.CreateExpenseDTO;
import pl.age.edu.api.expense.dto.CreateGroupExpenseDTO;
import pl.age.edu.models.Expense;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ExpenseApi {
    private final static ExpenseApiService expenseService =
            RetrofitClient.getRetrofitClient().create(ExpenseApiService.class);

    public static List<Expense> getAll() {
        Optional<Response<List<Expense>>> response = Optional.empty();
        try {
            response = Optional.of(expenseService.getExpenses().execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body).orElse(Collections.emptyList());
    }

    public static Optional<Expense> createPersonalExpense(CreateExpenseDTO dto) {
        Optional<Response<Expense>> response = Optional.empty();
        try {
            response = Optional.of(expenseService.createPersonalExpense(dto).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body);
    }

    public static List<Expense> createGroupExpense(CreateGroupExpenseDTO dto) {
        Optional<Response<List<Expense>>> response = Optional.empty();
        try {
            response = Optional.of(expenseService.createGroupExpense(dto).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body).orElse(Collections.emptyList());
    }
}
