package pl.edu.agh.debtexecutor.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    public Group getGroup(UUID id) {
        return groupRepository.findById(id).orElseThrow(IllegalStateException::new);
    }
}
