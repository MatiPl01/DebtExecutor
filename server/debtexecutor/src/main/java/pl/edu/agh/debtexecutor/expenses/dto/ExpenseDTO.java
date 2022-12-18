package pl.edu.agh.debtexecutor.expenses.dto;

import pl.edu.agh.debtexecutor.expenses.Expense;
import pl.edu.agh.debtexecutor.groups.Group;
import pl.edu.agh.debtexecutor.users.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record ExpenseDTO(
        String title,
        ExpenseUserDTO payer,
        ExpenseUserDTO payee,
        Optional<ExpenseGroupDTO> group,
        BigDecimal amount,
        LocalDateTime date
) {

    public static ExpenseDTO from(Expense expense) {
        return new ExpenseDTO(
                expense.getTitle(),
                ExpenseUserDTO.from(expense.getPayer()),
                ExpenseUserDTO.from(expense.getPayee()),
                expense.getGroup().map(ExpenseGroupDTO::from),
                expense.getAmount(),
                expense.getDate()
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

    private record ExpenseGroupDTO(UUID id, String name) {
        static ExpenseGroupDTO from(Group group) {
            return new ExpenseGroupDTO(group.getId(), group.getName());
        }
    }
}
