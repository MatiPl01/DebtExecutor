package pl.edu.agh.debtexecutor.users;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.debtexecutor.graphs.SimplifiedGraphService;
import pl.edu.agh.debtexecutor.users.dto.CreateUserDTO;
import pl.edu.agh.debtexecutor.users.model.User;
import pl.edu.agh.debtexecutor.users.repository.UserRepository;

import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SimplifiedGraphService simplifiedGraphService;

    public UserService(UserRepository userRepository,
                       SimplifiedGraphService simplifiedGraphService) {
        this.userRepository = userRepository;
        this.simplifiedGraphService = simplifiedGraphService;
        // Add existing users to the simplifies expenses graph
        simplifiedGraphService.addUsers(userRepository.findAll());
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(CreateUserDTO dto) throws ResponseStatusException {
        // Validate user credentials
        StringJoiner joiner = new StringJoiner(", ");
        if (dto.login().isEmpty()) joiner.add("login");
        if (dto.firstName().isEmpty()) joiner.add("firstName");
        if (dto.lastName().isEmpty()) joiner.add("lastName");

        if (joiner.toString().length() > 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to create a user. Missing fields: " + joiner
            );
        }

        if (userRepository.findByLogin(dto.login()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User with login '" + dto.login() + "' already exists"
            );
        }

        User user = new User(dto.login(), dto.firstName(), dto.lastName());
        userRepository.save(user);
        simplifiedGraphService.addUser(user);
        return user;
    }

    public User getUserById(UUID id) throws ResponseStatusException {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User with id " + id + " does not exist"
                )
        );
    }

    public User getUserByLogin(String login) throws ResponseStatusException {
        return userRepository.findByLogin(login).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User with login '" + login + "' does not exist"
                )
        );
    }

    public List<User> getUsersById(List<UUID> ids) {
        return userRepository.findAllById(ids);
    }
}
