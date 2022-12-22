package pl.edu.agh.debtexecutor.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.group.GroupApi;
import pl.edu.agh.debtexecutor.api.group.dto.CreateGroupDTO;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.models.Group;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class GroupService {
    private final ObservableList<Group> groups =
            FXCollections.observableArrayList();

    private final GroupApi groupApi;

    private GroupService(GroupApi groupApi) {
        this.groupApi = groupApi;
    }

    public void fetchData() {
        setGroups(groupApi.getAll());
    }

    public ObservableList<Group> getGroups() {
        return groups;
    }

    public Optional<Group> addGroup(String groupName, List<User> users) {
        List<String> userIds = users
                .stream()
                .map(User::getId)
                .toList();
        CreateGroupDTO dto = new CreateGroupDTO(groupName, userIds);
        Optional<Group> group = groupApi.createGroup(dto);
        group.ifPresent(groups::add);
        return group;
    }

    public void setGroups(List<Group> groups) {
        this.groups.setAll(sortedGroups(groups));
    }

    private List<Group> sortedGroups(List<Group> groups) {
        return groups.stream().sorted(Comparator.comparing(Group::toString)).toList();
    }
}