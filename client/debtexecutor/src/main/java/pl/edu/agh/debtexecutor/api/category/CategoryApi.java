package pl.edu.agh.debtexecutor.api.category;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.api.category.dto.CreateCategoryDTO;
import pl.edu.agh.debtexecutor.models.Category;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryApi {
    private final CategoryApiService categoryApiService;

    public CategoryApi(RetrofitClient retrofitClient) {
        categoryApiService = retrofitClient.getClient().create(CategoryApiService.class);
    }

    public List<Category> getAll() {
        try {
            List<Category> categories = categoryApiService.getCategories().execute().body();
            if (categories != null) return categories;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<Category> createCategory(CreateCategoryDTO dto) {
        try {
            return Optional.ofNullable(categoryApiService.createCategory(dto).execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
