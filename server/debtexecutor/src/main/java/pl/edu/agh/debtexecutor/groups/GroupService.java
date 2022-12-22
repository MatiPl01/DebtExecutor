package pl.edu.agh.debtexecutor.groups;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    public Group getGroupById(UUID id) throws ResponseStatusException {
        return groupRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Group with id " + id + " does not exist"
            )
        );
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }
}
