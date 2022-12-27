package pl.edu.agh.debtexecutor.expenses.dto;

import pl.edu.agh.debtexecutor.categories.dto.CategoryDTO;
import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.groups.model.Group;
import pl.edu.agh.debtexecutor.users.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record ExpenseDTO(
        UUID id,
        String title,
        ExpenseUserDTO payer,
        ExpenseUserDTO payee,
        BigDecimal amount,
        LocalDateTime date,
        Optional<ExpenseGroupDTO> group,
        Optional<CategoryDTO> category
) {
    public static ExpenseDTO from(Expense expense) {
        return new ExpenseDTO(
                expense.getId(),
                expense.getTitle(),
                ExpenseUserDTO.from(expense.getPayer()),
                ExpenseUserDTO.from(expense.getPayee()),
                expense.getAmount(),
                expense.getDate(),
                expense.getGroup().map(ExpenseGroupDTO::from),
                expense.getCategory().map(CategoryDTO::from)
        );
    }

    private record ExpenseUserDTO(UUID id, String firstName, String lastName) {
        static ExpenseUserDTO from(User user) {
            return new ExpenseUserDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName()
            );
        }
    }

    private record ExpenseGroupDTO(
            UUID id,
            String name,
            String placeholderName
    ) {
        static ExpenseGroupDTO from(Group group) {
            return new ExpenseGroupDTO(
                    group.getId(),
                    group.getName(),
                    group.getPlaceholderName()
            );
        }
    }
}
