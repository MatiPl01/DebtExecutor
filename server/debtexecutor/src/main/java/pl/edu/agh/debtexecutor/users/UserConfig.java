package pl.edu.agh.debtexecutor.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.debtexecutor.categories.model.Category;
import pl.edu.agh.debtexecutor.categories.repository.CategoryRepository;
import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.expenses.repository.ExpenseRepository;
import pl.edu.agh.debtexecutor.groups.model.Group;
import pl.edu.agh.debtexecutor.groups.repository.GroupRepository;
import pl.edu.agh.debtexecutor.users.model.User;
import pl.edu.agh.debtexecutor.users.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Configuration
public class UserConfig {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        GroupRepository groupRepository,
                                        ExpenseRepository expenseRepository,
                                        CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;

        return args -> {
            // Create users
            User ewa = addUser("ewa", "Ewa", "Miklewska");
            User kuba = addUser("kuba", "Jakub", "Stępień");
            User mateusz = addUser("mati", "Mateusz", "Łopaciński");
            User andrzej = addUser("andrzej", "Andrzej", "Nowak");
            List<User> users = List.of(ewa, kuba, mateusz, andrzej);

            // Create groups
            Group ewaKuba = addGroup(List.of(ewa, kuba));
            Group ewaKubaAndrzej = addGroup(List.of(ewa, kuba, andrzej));
            Group menGroup = addGroup("Men group", List.of(kuba, mateusz, andrzej));
            Group allGroup = addGroup("All group", List.of(ewa, kuba, mateusz, andrzej));
            List<Group> groups = List.of(ewaKuba, ewaKubaAndrzej, menGroup, allGroup);

            // Create categories
            Category food = addCategory("food");
            Category beverages = addCategory("beverages");
            Category entertainment = addCategory("entertainment");
            Category finances = addCategory("finances");
            List<Category> categories = List.of(food, beverages, entertainment);

            // Create expenses
            addExpense("Water", ewa, kuba, new BigDecimal("2.50"), beverages);
            addExpense("Coffee", ewa, mateusz, new BigDecimal("8.00"), beverages);
            addExpense("Bread", kuba, ewa, new BigDecimal("5.00"), food);
            addExpense("Cinema ticket", kuba, mateusz, new BigDecimal("25.00"), entertainment);
            addExpense("Cash loan", mateusz, ewa, new BigDecimal("10.00"), finances);

            addExpense("Pizza", mateusz, allGroup, new BigDecimal("75.00"), food);
            addExpense("Bowling", kuba, ewaKubaAndrzej, new BigDecimal("50.00"), entertainment);
            addExpense("Paintball", andrzej, menGroup, new BigDecimal("100.00"), entertainment);

            categoryRepository.saveAll(categories);
            groupRepository.saveAll(groups);
            userRepository.saveAll(users);
        };
    }

    private User addUser(String login, String firstName, String lastName) {
        User user = new User(login, firstName, lastName);

        userRepository.save(user);
        return user;
    }

    private Group addGroup(String name, List<User> members) {
        Group group = Optional.ofNullable(name).map(s -> new Group(s, members))
                              .orElseGet(() -> new Group(members));
        members.forEach(member -> member.addToGroup(group));

        groupRepository.save(group);
        return group;
    }

    private Group addGroup(List<User> members) {
        return addGroup(null, members);
    }

    private Expense addExpense(String title,
                               User payer,
                               User payee,
                               BigDecimal amount,
                               Category category) {
        Expense expense = new Expense(title, payer, payee, amount, category);
        payer.changeBalance(payee, amount);
        payee.changeBalance(payer, amount.negate());
        if (category != null) category.addExpense(expense);
        expenseRepository.save(expense);
        return expense;
    }

    private List<Expense> addExpense(String title,
                                     User payer,
                                     Group group,
                                     BigDecimal amount,
                                     Category category) {
        List<User> payees = group
                .getMembers()
                .stream()
                .filter(user -> user != payer)
                .toList();

        List<Expense> expenses = payees.stream()
                                       .map(user -> new Expense(
                                               title,
                                               payer,
                                               user,
                                               amount,
                                               group,
                                               category
                                       ))
                                       .toList();

        expenses.forEach(expense -> {
            payer.changeBalance(expense.getPayee(), amount);
            expense.getPayee().changeBalance(payer, amount.negate());
            if (category != null) category.addExpense(expense);
        });

        expenseRepository.saveAll(expenses);
        return expenses;
    }

    private Category addCategory(String name) {
        Category category = new Category(name);

        categoryRepository.save(category);
        return category;
    }
}
