package pl.age.edu.api.expense;

import pl.age.edu.api.expense.dto.CreateExpenseDTO;
import pl.age.edu.api.expense.dto.CreateGroupExpenseDTO;
import pl.age.edu.models.Expense;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface ExpenseService {
    @GET("expenses")
    Call<List<Expense>> getExpenses();

    @POST("expenses/add/expense")
    Call<Void> addExpense(@Body CreateExpenseDTO dto);

    @POST("expenses/add/expense")
    Call<Void> addGroupExpense(@Body CreateGroupExpenseDTO dto);
}
