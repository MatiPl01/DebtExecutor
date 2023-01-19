package pl.edu.agh.debtexecutor.services;

import jakarta.annotation.PreDestroy;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.api.group.GroupApi;
import pl.edu.agh.debtexecutor.api.group.dto.CreateGroupDTO;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.models.Group;
import pl.edu.agh.debtexecutor.utils.Interval;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {
    private static final int FETCH_INTERVAL = 10000; // 10s

    private final ObservableList<Group> groups =
            FXCollections.observableArrayList();
    private final GroupApi groupApi;
    private final Interval reFetchInterval;

    private GroupService(GroupApi groupApi) {
        this.groupApi = groupApi;
        reFetchInterval = new Interval(this::reFetch, FETCH_INTERVAL);
        reFetchInterval.start();
    }

    @PreDestroy
    public void beforeDestroy() {
        reFetchInterval.clear();
    }

    public void fetchData() {
        setGroups(groupApi.getAll());
        reFetchInterval.reset();
    }

    public ObservableList<Group> getGroups() {
        return groups;
    }

    public Optional<Group> addGroup(String groupName, List<User> users) {
        List<UUID> userIds = users
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

    private void reFetch() {
        Platform.runLater(() -> {
            setGroups(groupApi.getAll());
        });
    }
}
