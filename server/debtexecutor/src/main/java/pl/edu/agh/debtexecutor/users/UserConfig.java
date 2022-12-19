package pl.edu.agh.debtexecutor.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                                        ExpenseRepository expenseRepository) {
        return args -> {
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

            Group group = new Group(List.of(ewa, mateusz, kuba));
            ewa.addToGroup(group);
            mateusz.addToGroup(group);
            kuba.addToGroup(group);

            Expense expenseEwaKuba =new Expense(
                    "chleb",
                    kuba,
                    ewa,
                    BigDecimal.valueOf(2)
            );

            ewa.changeBalance(kuba, BigDecimal.valueOf(2));
            kuba.changeBalance(ewa, BigDecimal.valueOf(-2));

            Expense expenseKubaMateusz = new Expense(
                    "woda",
                    mateusz,
                    kuba,
                    group,
                    BigDecimal.valueOf(40)
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
