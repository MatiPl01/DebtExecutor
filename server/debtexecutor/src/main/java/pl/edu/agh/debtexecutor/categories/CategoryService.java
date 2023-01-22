package pl.edu.agh.debtexecutor.categories;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.debtexecutor.categories.dto.CreateCategoryDTO;
import pl.edu.agh.debtexecutor.categories.model.Category;
import pl.edu.agh.debtexecutor.categories.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CreateCategoryDTO dto)
            throws ResponseStatusException {
        if (dto.name().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to create a category. Missing fields: name"
            );
        }

        if (categoryRepository.findByName(dto.name()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Category with name '" + dto.name() + "' already exists"
            );
        }

        Category category = new Category(dto.name());
        categoryRepository.save(category);
        return category;
    }

    public Category getCategoryById(UUID id) throws ResponseStatusException {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Category with id " + id + "does not exist"
                )
        );
    }

    public List<Category> getCategoriesById(List<UUID> ids) {
        return categoryRepository.findAllById(ids);
    }
}
