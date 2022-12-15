package pl.edu.agh.debtexecutor.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.debtexecutor.group.Group;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
