package pl.edu.agh.debtexecutor.api.expense;

import pl.edu.agh.debtexecutor.api.expense.dto.CreateExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateGroupExpenseDTO;
import pl.edu.agh.debtexecutor.models.Expense;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface ExpenseApiService {
    @GET("expenses")
    Call<List<Expense>> getExpenses();

    @POST("expenses")
    Call<Expense> createPersonalExpense(@Body CreateExpenseDTO dto);

    @POST("expenses/group")
    Call<List<Expense>> createGroupExpense(@Body CreateGroupExpenseDTO dto);
}
