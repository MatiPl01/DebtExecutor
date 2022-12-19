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
    public static List<Expense> getAll() {
        ExpenseApiService service = RetrofitClient.getRetrofitClient().create(
                ExpenseApiService.class);
        Optional<Response<List<Expense>>> response = Optional.empty();
        try {
            response = Optional.of(service.getExpenses().execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body).orElse(Collections.emptyList());
    }

    public static void addPersonal(CreateExpenseDTO dto) {
        ExpenseApiService service = RetrofitClient.getRetrofitClient().create(
                ExpenseApiService.class);
        try {
            service.addExpense(dto).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addGroup(CreateGroupExpenseDTO dto) {
        ExpenseApiService service = RetrofitClient.getRetrofitClient().create(
                ExpenseApiService.class);
        try {
            service.addGroupExpense(dto).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
