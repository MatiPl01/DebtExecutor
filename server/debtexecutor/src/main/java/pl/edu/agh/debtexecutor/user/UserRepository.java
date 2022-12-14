package pl.edu.agh.debtexecutor.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllByGroupId(UUID groupId); //TODO change to group
}
