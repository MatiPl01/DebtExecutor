package pl.edu.agh.debtexecutor.expense;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.group.Group;
import pl.edu.agh.debtexecutor.group.GroupService;
import pl.edu.agh.debtexecutor.user.User;

import java.math.BigDecimal;
import java.util.List;

import pl.edu.agh.debtexecutor.user.UserService;

@Component
public class ExpenseFactory {
    private final UserService userService;
    private final GroupService groupService;

    public ExpenseFactory(UserService userService, GroupService groupService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    Expense createExpense(CreateExpenseDTO dto) {
        User payee = userService.getUserById(dto.payee()).orElseThrow(IllegalStateException::new);
        User payer = userService.getUserById(dto.payer()).orElseThrow(IllegalStateException::new);
        BigDecimal amount = dto.amount();
        payer.changeBalance(payee, amount);
        payee.changeBalance(payer, amount.negate());
        return new Expense(dto.title(), payer, payee, amount);
    }


    List<Expense> createExpense(CreateGroupExpenseDTO dto) {
        User payer = userService.getUserById(dto.payer()).orElseThrow(IllegalStateException::new);
        Group group = groupService.getGroup(dto.group());

        List<User> users = group.getMembers()
                .stream()
                .filter(user -> user != payer)
                .toList();
        BigDecimal amount = dto.amount().divide(BigDecimal.valueOf(users.size(), 0));

        return users.stream()
                .map(payee -> {
                    payer.changeBalance(payee, amount);
                    payee.changeBalance(payer, amount.negate());
                    Expense expense = new Expense(dto.title(), payer, payee, amount);
                    group.addExpense(expense);
                    return expense;
                })
                .toList();
    }

}
