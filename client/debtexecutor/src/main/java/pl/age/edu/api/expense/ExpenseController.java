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

public class ExpenseController {
    public static List<Expense> getAll() {
        ExpenseService service = RetrofitClient.getRetrofitClient().create(ExpenseService.class);
        Optional<Response<List<Expense>>> response = Optional.empty();
        try {
            response = Optional.of(service.getExpenses().execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body).orElse(Collections.emptyList());
    }

    public static void addPersonal(CreateExpenseDTO dto) {
        ExpenseService service = RetrofitClient.getRetrofitClient().create(ExpenseService.class);
        try {
            service.addExpense(dto).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addGroup(CreateGroupExpenseDTO dto) {
        ExpenseService service = RetrofitClient.getRetrofitClient().create(ExpenseService.class);
        try {
            service.addGroupExpense(dto).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
