package pl.edu.agh.debtexecutor.categories;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.debtexecutor.categories.dto.CategoryDTO;
import pl.edu.agh.debtexecutor.categories.dto.CreateCategoryDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public @ResponseBody List<CategoryDTO> getCategories() {
        return categoryService.getCategories()
                              .stream()
                              .map(CategoryDTO::from)
                              .toList();
    }

    @PostMapping
    public @ResponseBody CategoryDTO createCategory(
            @RequestBody CreateCategoryDTO dto
    ) throws ResponseStatusException {
        if (dto.name().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to create a category. Missing fields: name"
            );
        }

        return CategoryDTO.from(categoryService.createCategory(dto));
    }

    @GetMapping("/{categoryId}")
    public @ResponseBody CategoryDTO getCategoryById(
            @PathVariable String categoryId
    ) throws ResponseStatusException {
        return CategoryDTO.from(categoryService.getCategoryById(UUID.fromString(
                categoryId)));
    }
}
