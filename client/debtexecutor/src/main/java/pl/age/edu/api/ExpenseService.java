package pl.age.edu.api;

import pl.age.edu.models.Expense;
import pl.age.edu.models.Group;
import pl.age.edu.utils.CreateUserDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface ExpenseService {
    @GET("api/v1/expenses")
    Call<List<Expense>> getExpenses();
}
