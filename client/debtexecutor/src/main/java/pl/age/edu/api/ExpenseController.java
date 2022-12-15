package pl.age.edu.api;

import pl.age.edu.models.Expense;
import pl.age.edu.models.Group;
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
}
