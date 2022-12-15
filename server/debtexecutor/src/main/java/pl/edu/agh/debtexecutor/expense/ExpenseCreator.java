package pl.edu.agh.debtexecutor.expense;

import pl.edu.agh.debtexecutor.group.Group;
import pl.edu.agh.debtexecutor.user.User;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseCreator {
    public static List<Expense> createExpense(User payer, User payee, BigDecimal amount) {
        // TODO
    }

    public static List<Expense> createExpense(User payer, Group group, BigDecimal amount) {
        // TODO
    }
}
