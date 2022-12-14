package pl.edu.agh.debtexecutor.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.debtexecutor.expense.Expense;
import pl.edu.agh.debtexecutor.expense.ExpenseRepository;
import pl.edu.agh.debtexecutor.group.Group;
import pl.edu.agh.debtexecutor.group.GroupRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        GroupRepository groupRepository,
                                        ExpenseRepository expenseRepository) {
        return args -> {
            User ewa = new User(
                    "Ewa",
                    "Miklewska"
            );

            User kuba = new User(
                    "Jakub",
                    "Stępień"
            );

            User mateusz = new User(
                    "Mateusz",
                    "Łopaciński"
            );

            userRepository.saveAll(
                    List.of(ewa, kuba, mateusz)
            );

            Group group = new Group(List.of(ewa, mateusz, kuba));
            ewa.addToGroup(group);
            mateusz.addToGroup(group);
            kuba.addToGroup(group);

            Expense expenseEwaKuba = new Expense(
                    "hlep",
                    kuba,
                    ewa,
                    BigDecimal.valueOf(2),
                    LocalDateTime.of(2022, 12, 8, 14, 15, 0)
            );

            ewa.changeBalance(kuba, BigDecimal.valueOf(2));
            kuba.changeBalance(ewa, BigDecimal.valueOf(-2));

            Expense expenseKubaMateusz = new Expense(
                    "wóda",
                    mateusz,
                    kuba,
                    group,
                    BigDecimal.valueOf(40),
                    LocalDateTime.of(2022, 12, 8, 14, 15, 15)
            );

            kuba.changeBalance(mateusz, BigDecimal.valueOf(40));
            mateusz.changeBalance(kuba, BigDecimal.valueOf(-40));

            group.addExpense(expenseKubaMateusz);

            groupRepository.save(group);
            expenseRepository.saveAll(List.of(expenseEwaKuba, expenseKubaMateusz));

            userRepository.saveAll(
                    List.of(ewa, kuba, mateusz)
            );
        };
    }
}
