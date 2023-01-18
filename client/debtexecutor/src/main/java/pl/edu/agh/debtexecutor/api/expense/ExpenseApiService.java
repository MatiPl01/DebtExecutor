package pl.edu.agh.debtexecutor.api.expense;

import pl.edu.agh.debtexecutor.api.expense.dto.CreateExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateGroupExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.GetExpensesResponseDTO;
import pl.edu.agh.debtexecutor.models.Expense;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface ExpenseApiService {
    @GET("expenses")
    Call<GetExpensesResponseDTO> getExpenses(
        @Query("pageSize") int pageSize,
        @Query("pageNumber") int pageNumber,
        @Query("sortBy") String sortBy,
        @Query("sortDirection") String sortDirection
    );

    @GET("expenses")
    Call<GetExpensesResponseDTO> getExpenses(
        @Query("pageSize") int pageSize,
        @Query("pageNumber") int pageNumber,
        @Query("sortBy") String sortBy,
        @Query("sortDirection") String sortDirection,
        @Query("categories") String categories
    );

    @POST("expenses")
    Call<Expense> createPersonalExpense(@Body CreateExpenseDTO dto);

    @POST("expenses/group")
    Call<List<Expense>> createGroupExpense(@Body CreateGroupExpenseDTO dto);
}
