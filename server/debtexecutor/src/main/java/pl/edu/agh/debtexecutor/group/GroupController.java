package pl.edu.agh.debtexecutor.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.debtexecutor.user.User;
import pl.edu.agh.debtexecutor.user.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/groups")
public class GroupController {
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping
    public List<GroupDTO> getGroups() {
        return groupService.getGroups().stream().map(GroupDTO::from).toList();
    }

    @PostMapping
    public void addGroup(@RequestBody CreateGroupDTO dto) {
        List<User> users = userService.getUsersById(dto.users());
        Group group = new Group(dto.name(), users);
        groupService.addGroup(group);
        users.forEach(user -> user.addToGroup(group));
    }
}
