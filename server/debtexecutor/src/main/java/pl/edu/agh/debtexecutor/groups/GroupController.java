package pl.edu.agh.debtexecutor.groups;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.debtexecutor.groups.dto.CreateGroupDTO;
import pl.edu.agh.debtexecutor.groups.dto.GroupDTO;
import pl.edu.agh.debtexecutor.groups.model.Group;
import pl.edu.agh.debtexecutor.users.model.User;
import pl.edu.agh.debtexecutor.users.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/groups")
public class GroupController {
    private final GroupService groupService;
    private final UserService userService;

    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody List<GroupDTO> getGroups() {
        return groupService.getGroups().stream().map(GroupDTO::from).toList();
    }

    @PostMapping
    public @ResponseBody GroupDTO createGroup(@RequestBody CreateGroupDTO dto) {
        List<User> members = userService.getUsersById(dto.members());
        Group group = new Group(dto.name(), members);
        groupService.addGroup(group);
        members.forEach(user -> user.addToGroup(group));
        return GroupDTO.from(group);
    }

    @GetMapping("/{groupID}")
    public @ResponseBody GroupDTO getGroup(
            @PathVariable String groupID
    ) throws ResponseStatusException {
        return GroupDTO.from(groupService.getGroupById(UUID.fromString(groupID)));
    }
}
