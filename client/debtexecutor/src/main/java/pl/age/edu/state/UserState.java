package pl.age.edu.state;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import pl.age.edu.api.user.UserApi;
import pl.age.edu.models.User;

import java.util.List;

@Component
public class UserState {

    private final ObservableList<User> users =
            FXCollections.observableArrayList();

    private final UserApi userApi;

    private UserState(UserApi userAPi) {
        this.userApi = userAPi;
    }

    public void fetchData() {
        users.setAll(userApi.getAll());
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void setUsers(List<User> users) {
        this.users.setAll(users);
    }
}
