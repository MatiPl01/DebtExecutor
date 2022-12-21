package pl.age.edu.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import pl.age.edu.api.group.GroupApi;
import pl.age.edu.api.group.dto.CreateGroupDTO;
import pl.age.edu.models.Group;
import pl.age.edu.models.User;

import java.util.List;
import java.util.Optional;

@Component
public class GroupState {
    private final ObservableList<Group> groups =
            FXCollections.observableArrayList();

    private final GroupApi groupApi;

    private GroupState(GroupApi groupApi) {
        this.groupApi = groupApi;
    }

    public void fetchData() {
        groups.setAll(groupApi.getAll());
    }

    public ObservableList<Group> getGroups() {
        return groups;
    }

    public void addGroup(String groupName, List<User> users) {
        List<String> userIds = users
                .stream()
                .map(User::getId)
                .toList();
        CreateGroupDTO dto = new CreateGroupDTO(groupName, userIds);
        Optional<Group> group = groupApi.createGroup(dto);
        group.ifPresent(groups::add);
    }
    public void setGroups(List<Group> groups) {
        this.groups.setAll(groups);
    }
}
