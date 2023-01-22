package pl.edu.agh.debtexecutor.api.category;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.ApiResponseHandler;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.api.category.dto.CreateCategoryDTO;
import pl.edu.agh.debtexecutor.models.Category;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryApi {
    private final CategoryApiService categoryApiService;
    private final ApiResponseHandler handler;

    public CategoryApi(RetrofitClient retrofitClient, ApiResponseHandler handler) {
        this.handler = handler;
        categoryApiService = retrofitClient.getClient().create(CategoryApiService.class);
    }

    public List<Category> getAll() {
        Optional<List<Category>> categories = handler.handleResponse(categoryApiService.getCategories());
        return categories.orElse(Collections.emptyList());
    }

    public Optional<Category> createCategory(CreateCategoryDTO dto) {
        return handler.handleResponse(categoryApiService.createCategory(dto));
    }
}
