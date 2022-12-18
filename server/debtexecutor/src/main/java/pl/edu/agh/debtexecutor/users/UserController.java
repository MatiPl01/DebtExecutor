package pl.edu.agh.debtexecutor.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.debtexecutor.users.dto.CreateUserDTO;
import pl.edu.agh.debtexecutor.users.dto.LoginUserDTO;
import pl.edu.agh.debtexecutor.users.dto.UserDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(UserDTO::from).toList();
    }

    @PostMapping
    public @ResponseBody UserDTO signUp(
            @RequestBody CreateUserDTO dto
    ) throws ResponseStatusException {
        return UserDTO.from(userService.createUser(dto));
    }

    @PostMapping("/login")
    public @ResponseBody UserDTO signIn(
            @RequestBody LoginUserDTO dto
    ) throws ResponseStatusException {
        return UserDTO.from(userService.getUserByLogin(dto.login()));
    }

    @GetMapping("/{userId}")
    public @ResponseBody UserDTO getUserById(
            @PathVariable String userId
    ) throws ResponseStatusException {
        return UserDTO.from(userService.getUserById(UUID.fromString(userId)));
    }
}
