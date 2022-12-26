package pl.edu.agh.debtexecutor.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.debtexecutor.categories.Category;
import pl.edu.agh.debtexecutor.categories.CategoryRepository;
import pl.edu.agh.debtexecutor.expenses.Expense;
import pl.edu.agh.debtexecutor.expenses.ExpenseRepository;
import pl.edu.agh.debtexecutor.groups.Group;
import pl.edu.agh.debtexecutor.groups.GroupRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        GroupRepository groupRepository,
                                        ExpenseRepository expenseRepository,
                                        CategoryRepository categoryRepository) {
        return args -> {
            // Create users
            User ewa = new User(
                    "ewa",
                    "Ewa",
                    "Miklewska"
            );

            User kuba = new User(
                    "kuba",
                    "Jakub",
                    "Stępień"
            );

            User mateusz = new User(
                    "mati",
                    "Mateusz",
                    "Łopaciński"
            );

            userRepository.saveAll(
                    List.of(ewa, kuba, mateusz)
            );

            // Create groups
            Group group = new Group(List.of(ewa, mateusz, kuba));
            ewa.addToGroup(group);
            mateusz.addToGroup(group);
            kuba.addToGroup(group);

            // Create categories
            Category food = new Category("food");
            Category beverages = new Category("beverages");

            // Create expenses
            Expense expenseEwaKuba = new Expense(
                    "bread",
                    kuba,
                    ewa,
                    List.of(food),
                    BigDecimal.valueOf(2)
            );

            ewa.changeBalance(kuba, BigDecimal.valueOf(2));
            kuba.changeBalance(ewa, BigDecimal.valueOf(-2));
            food.addExpense(expenseEwaKuba);

            Expense expenseKubaMateusz = new Expense(
                    "water",
                    mateusz,
                    kuba,
                    group,
                    List.of(food, beverages),
                    BigDecimal.valueOf(40)
            );

            kuba.changeBalance(mateusz, BigDecimal.valueOf(40));
            mateusz.changeBalance(kuba, BigDecimal.valueOf(-40));
            group.addExpense(expenseKubaMateusz);
            food.addExpense(expenseKubaMateusz);
            beverages.addExpense(expenseKubaMateusz);

            groupRepository.save(group);
            categoryRepository.saveAll(List.of(
                    food,
                    beverages
            ));
            expenseRepository.saveAll(List.of(
                    expenseEwaKuba,
                    expenseKubaMateusz
            ));
            userRepository.saveAll(
                    List.of(ewa, kuba, mateusz)
            );
        };
    }
}
