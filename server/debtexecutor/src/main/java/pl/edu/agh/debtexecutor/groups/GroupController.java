package pl.edu.agh.debtexecutor.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.debtexecutor.groups.dto.CreateGroupDTO;
import pl.edu.agh.debtexecutor.groups.dto.GroupDTO;
import pl.edu.agh.debtexecutor.users.User;
import pl.edu.agh.debtexecutor.users.UserService;

import java.util.List;
import java.util.UUID;

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
    public @ResponseBody List<GroupDTO> getGroups() {
        return groupService.getGroups().stream().map(GroupDTO::from).toList();
    }

    @PostMapping
    public void createGroup(@RequestBody CreateGroupDTO dto) {
        List<User> users = userService.getUsersById(dto.users());
        Group group = new Group(dto.name(), users);
        groupService.addGroup(group);
        users.forEach(user -> user.addToGroup(group));
    }

    @GetMapping("/{groupID}")
    public @ResponseBody GroupDTO getGroup(
            @PathVariable String groupID
    ) throws ResponseStatusException {
        return GroupDTO.from(groupService.getGroupById(UUID.fromString(groupID)));
    }
}
