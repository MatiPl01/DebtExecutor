package pl.age.edu.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import pl.age.edu.models.Group;

import java.util.List;

@Component
public class GroupState {
    private final ObservableList<Group> groups =
            FXCollections.observableArrayList();

    public ObservableList<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void setGroups(List<Group> groups) {
        this.groups.setAll(groups);
    }
}
