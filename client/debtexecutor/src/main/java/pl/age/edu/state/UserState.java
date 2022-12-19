package pl.age.edu.state;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Repository;
import pl.age.edu.models.User;

@Repository
public class UserState {
    private final SimpleObjectProperty<User> loggedInUser =
            new SimpleObjectProperty<>();
    private final ObservableList<User> users =
            FXCollections.observableArrayList();

    public User getLoggedInUser() {
        return loggedInUser.get();
    }

    public SimpleObjectProperty<User> loggedInUserProperty() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser.set(loggedInUser);
    }

    public ObservableList<User> getUsers() {
        return users;
    }
}
