package pl.edu.agh.debtexecutor.api.category;

import pl.edu.agh.debtexecutor.api.category.dto.CreateCategoryDTO;
import pl.edu.agh.debtexecutor.models.Category;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface CategoryApiService {
    @GET("categories")
    Call<List<Category>> getCategories();

    @POST("categories")
    Call<Category> createCategory(@Body CreateCategoryDTO dto);
}
