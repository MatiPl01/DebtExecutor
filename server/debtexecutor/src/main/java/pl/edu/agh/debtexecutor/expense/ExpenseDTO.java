package pl.edu.agh.debtexecutor.expense;

import pl.edu.agh.debtexecutor.group.Group;
import pl.edu.agh.debtexecutor.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

record ExpenseDTO(
        String title,
        ExpenseUserDTO payer,
        ExpenseUserDTO payee,
        Optional<ExpenseGroupDTO> group,
        BigDecimal amount,
        LocalDateTime date
    ) {

    static ExpenseDTO from(Expense expense) {
        return new ExpenseDTO(
            expense.getTitle(),
            ExpenseUserDTO.from(expense.getPayer()),
            ExpenseUserDTO.from(expense.getPayee()),
            expense.getGroup().map(ExpenseGroupDTO::from),
            expense.getAmount(),
            expense.getDate()
        );
    }

    private record ExpenseUserDTO(String firstName, String lastName) {
        static ExpenseUserDTO from(User user) {
            return new ExpenseUserDTO(user.getFirstName(), user.getLastName());
        }
    }

    private record ExpenseGroupDTO(String name) {
        static ExpenseGroupDTO from(Group group) {
            return new ExpenseGroupDTO(group.getName());
        }
    }
}
