package pl.edu.agh.debtexecutor.expense;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.group.GroupService;
import pl.edu.agh.debtexecutor.user.User;

import java.math.BigDecimal;
import java.util.List;

import pl.edu.agh.debtexecutor.user.UserService;

@Component
public class ExpenseFactory {
    private final UserService userService;

    public ExpenseFactory(UserService userService) {
        this.userService = userService;
    }

    Expense createExpense(CreateExpenseDTO dto) {
        User payee = userService.getUserById(dto.payee()).orElseThrow(IllegalStateException::new);
        User payer = userService.getUserById(dto.payer()).orElseThrow(IllegalStateException::new);
        return getExpense(dto.title(), payer, payee, dto.amount());
    }


    List<Expense> createExpense(CreateGroupExpenseDTO dto) {
        User payer = userService.getUserById(dto.payer()).orElseThrow(IllegalStateException::new);

        List<User> users = userService.getUsersByGroupId(dto.group())
                .stream()
                .filter(user -> user != payer)
                .toList();
        BigDecimal amount = dto.amount().divide(BigDecimal.valueOf(users.size(), 2));

        return users.stream()
                .map(payee -> getExpense(dto.title(), payer, payee, amount))
                .toList();
    }

    private static Expense getExpense(String title, User payer, User payee, BigDecimal amount) {
        payer.changeBalance(payee, amount);
        payee.changeBalance(payer, amount.negate());
        return new Expense(title, payer, payee, amount);
    }
}
