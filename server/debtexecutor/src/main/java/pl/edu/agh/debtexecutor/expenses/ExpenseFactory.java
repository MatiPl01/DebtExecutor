package pl.edu.agh.debtexecutor.expenses;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.categories.Category;
import pl.edu.agh.debtexecutor.categories.CategoryService;
import pl.edu.agh.debtexecutor.expenses.dto.CreateExpenseDTO;
import pl.edu.agh.debtexecutor.expenses.dto.CreateGroupExpenseDTO;
import pl.edu.agh.debtexecutor.groups.Group;
import pl.edu.agh.debtexecutor.groups.GroupService;
import pl.edu.agh.debtexecutor.users.User;
import pl.edu.agh.debtexecutor.users.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class ExpenseFactory {
    private final UserService userService;
    private final GroupService groupService;
    private final CategoryService categoryService;

    public ExpenseFactory(UserService userService,
                          GroupService groupService,
                          CategoryService categoryService) {
        this.groupService = groupService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public Expense createExpense(CreateExpenseDTO dto) {
        User payee = userService.getUserById(dto.payee());
        User payer = userService.getUserById(dto.payer());
        List<Category> categories =
                categoryService.getCategoriesById(dto.categories());
        BigDecimal amount = dto.amount();

        return new Expense(dto.title(), payer, payee, categories, amount);
    }

    public List<Expense> createExpense(CreateGroupExpenseDTO dto) {
        User payer = userService.getUserById(dto.payer());
        Group group = groupService.getGroupById(dto.group());
        List<Category> categories =
                categoryService.getCategoriesById(dto.categories());
        List<User> users = group
                .getMembers()
                .stream()
                .filter(user -> user != payer)
                .toList();
        BigDecimal amount = dto
                .amount()
                .divide(
                        BigDecimal.valueOf(users.size(), 0),
                        RoundingMode.CEILING
                );

        return users
                .stream()
                .map(payee -> new Expense(
                        dto.title(),
                        payer,
                        payee,
                        group,
                        categories,
                        amount
                ))
                .toList();
    }
}
