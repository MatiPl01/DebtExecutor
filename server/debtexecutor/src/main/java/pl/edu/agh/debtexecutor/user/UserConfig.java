package pl.edu.agh.debtexecutor.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User ewa = new User(
                "ewa123",
                "Ewa",
                "Miklewska"
            );

            User kuba = new User(
                "kuba123",
                "Jakub",
                "Stępień"
            );

            User mateusz = new User(
                "mateusz123",
                "Mateusz",
                "Łopaciński"
            );

            userRepository.saveAll(
                List.of(ewa, kuba, mateusz)
            );
        };
    }
}
