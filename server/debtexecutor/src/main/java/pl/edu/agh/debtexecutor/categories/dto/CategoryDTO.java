package pl.edu.agh.debtexecutor.categories.dto;

import pl.edu.agh.debtexecutor.categories.Category;

import java.util.UUID;

public record CategoryDTO(
        UUID id,
        String name
) {
    public static CategoryDTO from(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }
}
