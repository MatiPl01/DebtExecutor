package pl.edu.agh.debtexecutor.services;

import jakarta.annotation.PreDestroy;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.api.category.CategoryApi;
import pl.edu.agh.debtexecutor.api.category.dto.CreateCategoryDTO;
import pl.edu.agh.debtexecutor.models.Category;
import pl.edu.agh.debtexecutor.utils.Interval;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private static final int FETCH_INTERVAL = 10000; // 10s

    private final CategoryApi categoryApi;

    private final Interval reFetchInterval;
    private final ObservableList<Category> categories =
            FXCollections.observableArrayList();

    public CategoryService(CategoryApi categoryApi) {
        this.categoryApi = categoryApi;
        reFetchInterval = new Interval(this::reFetch, FETCH_INTERVAL);
        reFetchInterval.start();
    }

    @PreDestroy
    public void beforeDestroy() {
        reFetchInterval.clear();
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }

    public Optional<Category> addCategory(String name) {
        CreateCategoryDTO dto = new CreateCategoryDTO(name);
        Optional<Category> category = categoryApi.createCategory(dto);
        category.ifPresent(categories::add);
        return category;
    }

    public void fetchData() {
        setCategories(categoryApi.getAll());
        reFetchInterval.reset();
    }

    public void setCategories(List<Category> categories) {
        this.categories.setAll(sortedCategories(categories));
    }

    private List<Category> sortedCategories(List<Category> categories) {
        return categories.stream()
                         .sorted(Comparator.comparing(Category::getName))
                         .toList();
    }

    private void reFetch() {
        Platform.runLater(() -> setCategories(categoryApi.getAll()));
    }
}
