package pl.edu.agh.debtexecutor.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(UserDTO::from).toList();
    }

    @PostMapping
    public void addUser(@RequestBody CreateUserDTO dto) {
        User user = new User(dto.firstName(), dto.lastName());
        userService.addUser(user);
    }



    //TODO loginUser(login) -> user; createUser, createGroup, createExpense, getExpenses, getGroups, getUsers (?)
}
