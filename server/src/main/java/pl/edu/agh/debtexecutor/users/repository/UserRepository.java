package pl.edu.agh.debtexecutor.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.agh.debtexecutor.users.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.login = ?1")
    Optional<User> findByLogin(String login);
}
