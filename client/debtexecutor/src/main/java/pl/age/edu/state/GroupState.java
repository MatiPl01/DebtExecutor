package pl.age.edu.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.age.edu.models.Group;

public class GroupState {
    private final ObservableList<Group> groups =
            FXCollections.observableArrayList();

    public ObservableList<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }
}
